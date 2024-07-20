package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.outbound.LoginUserDTO;
import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.application.services.AuthenticationService;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;

public class AuthenticateUserUseCaseImpl extends AbstractUseCase implements AuhtenticateUserUseCase {
    private final AuthenticationService authenticationService;
    public AuthenticateUserUseCaseImpl(
            AnnotationBasedValidator annotationBasedValidator,
            AuthenticationService authenticationService
    ) {
        super(annotationBasedValidator);
        this.authenticationService = authenticationService;
    }
    @Override
    public String authenticate(LoginUserDTO loginUserDTO) throws InvalidCredentialsException {
        annotationBasedValidator.validateAccess(loginUserDTO);
        return authenticationService.authenticate(
                new Email(loginUserDTO.email()),
                loginUserDTO.password()
        );
    }
}
