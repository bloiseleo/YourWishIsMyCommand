package br.com.yourwishismycommand.infra;

import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.application.usecases.RegisterUserUseCase;
import br.com.yourwishismycommand.application.usecases.RegisterUserUseCaseImpl;
import br.com.yourwishismycommand.domain.repositories.UserRepository;
import br.com.yourwishismycommand.infra.repositoy.UserRepositoryImpl;
import br.com.yourwishismycommand.infra.services.JakartaBeanValidator;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
