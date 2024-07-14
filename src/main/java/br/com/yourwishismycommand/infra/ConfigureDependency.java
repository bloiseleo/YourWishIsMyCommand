package br.com.yourwishismycommand.infra;

import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.application.services.AuthenticationService;
import br.com.yourwishismycommand.application.services.JwtManagerService;
import br.com.yourwishismycommand.application.usecases.AuhtenticateUserUseCase;
import br.com.yourwishismycommand.application.usecases.AuthenticateUserUseCaseImpl;
import br.com.yourwishismycommand.application.usecases.RegisterUserUseCase;
import br.com.yourwishismycommand.application.usecases.RegisterUserUseCaseImpl;
import br.com.yourwishismycommand.domain.repositories.UserRepository;
import br.com.yourwishismycommand.infra.repositoy.UserRepositoryImpl;
import br.com.yourwishismycommand.infra.services.AuthenticationServiceImpl;
import br.com.yourwishismycommand.infra.services.JakartaBeanValidator;
import br.com.yourwishismycommand.infra.services.JwtServiceImpl;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class ConfigureDependency {
    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }
    @Bean
    public AnnotationBasedValidator annotationBasedValidator(Validator validator) {
        return new JakartaBeanValidator(validator);
    }
    @Bean
    public RegisterUserUseCase registerUserUseCase(AnnotationBasedValidator validator, UserRepository userRepository) {
        return new RegisterUserUseCaseImpl(validator, userRepository);
    }
    @Bean
    public AuthenticationService authenticationService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtManagerService jwtManagerService
    ) {
        return new AuthenticationServiceImpl(authenticationManager, userRepository, jwtManagerService);
    }
    @Bean
    public AuhtenticateUserUseCase auhtenticateUserUseCase(
            AnnotationBasedValidator annotationBasedValidator,
            AuthenticationService authenticationService
    ) {
        return new AuthenticateUserUseCaseImpl(annotationBasedValidator, authenticationService);
    }
    @Bean
    public JwtManagerService jwtManagerService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.issuer}") String issuer
    ) {
        return new JwtServiceImpl(secret, issuer);
    }
}
