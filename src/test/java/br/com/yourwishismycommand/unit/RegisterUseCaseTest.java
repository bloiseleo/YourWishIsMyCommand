package br.com.yourwishismycommand.unit;

import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.application.usecases.RegisterUserUseCaseImpl;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;
import br.com.yourwishismycommand.domain.exceptions.EmailAlreadyTakenException;
import br.com.yourwishismycommand.domain.repositories.UserRepository;
import br.com.yourwishismycommand.infra.dtos.UserDTO;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegisterUseCaseTest {
    @Mock
    private AnnotationBasedValidator annotationBasedValidator;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RegisterUserUseCaseImpl registerUserUseCase;
    @Test
    void createUser_ShouldProccedOk() {
        var dto = new UserDTO(
                "Teste",
                "12345678",
                "teste@email.com"
        );
        when(userRepository.userExists(any())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(new User(dto.name(), new Email(dto.email()), dto.password()));
        var user = registerUserUseCase.registerUser(dto);
        assertNotNull(user);
        assertEquals(dto.name(), user.getName());
        assertEquals(dto.email(), user.getEmail().toString());
    }
    @Test
    void createUser_FailBecauseAlreadyExists() {
        var dto = new UserDTO(
                "Teste",
                "12345678",
                "teste@email.com"
        );
        when(userRepository.userExists(any())).thenReturn(true);
        assertThrows(EmailAlreadyTakenException.class, () -> {
           registerUserUseCase.registerUser(dto);
        });
    }
    @Test
    void createUser_FailBecauseValidationFails() {
        var dto = new UserDTO(
                "Teste",
                "12345678",
                "teste@email.com"
        );
        doThrow(ApplicationValidationException.class).when(annotationBasedValidator).validateCreate(any());
        assertThrows(ApplicationValidationException.class, () -> {
           registerUserUseCase.registerUser(dto);
        });
    }
}
