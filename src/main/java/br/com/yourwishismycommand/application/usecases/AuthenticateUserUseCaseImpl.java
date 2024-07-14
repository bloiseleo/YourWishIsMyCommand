package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.outbound.LoginUserDTO;
import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.application.services.AuthenticationService;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;

public class AuthenticateUserUseCaseImpl implements AuhtenticateUserUseCase {
    private final AnnotationBasedValidator annotationBasedValidator;
    private final AuthenticationService authenticationService;
    public AuthenticateUserUseCaseImpl(AnnotationBasedValidator annotationBasedValidator, AuthenticationService authenticationService) {
        this.annotationBasedValidator = annotationBasedValidator;
        this.authenticationService = authenticationService;
    }
    @Override
    public void authenticate(LoginUserDTO loginUserDTO) throws InvalidCredentialsException {
        annotationBasedValidator.validateAccess(loginUserDTO);
        if(!authenticationService.authenticate(
                new Email(loginUserDTO.email()),
                loginUserDTO.password()
        )) {
            throw new InvalidCredentialsException();
        }
    }
}
