package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.RegisterUserDTO;
import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.domain.exceptions.EmailAlreadyTakenException;
import br.com.yourwishismycommand.domain.repositories.UserRepository;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
    private AnnotationBasedValidator validator;
    private UserRepository userRepository;
    public RegisterUserUseCaseImpl(AnnotationBasedValidator validator, UserRepository userRepository) {
        this.validator = validator;
        this.userRepository = userRepository;
    }
    @Override
    public User registerUser(RegisterUserDTO registerUserDTO) {
        validator.validateCreate(registerUserDTO);
        var user = new User(
                registerUserDTO.name(),
                new Email(registerUserDTO.email()),
                UserRole.valueOf(registerUserDTO.role()),
                registerUserDTO.password()
        );
        if(userRepository.userExists(user)) throw new EmailAlreadyTakenException(user.getEmail().toString());
        return userRepository.save(user);
    }
}
