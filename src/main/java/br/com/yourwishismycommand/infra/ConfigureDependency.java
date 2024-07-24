package br.com.yourwishismycommand.infra;

import br.com.yourwishismycommand.application.services.*;
import br.com.yourwishismycommand.application.strategy.CreateClientProfile;
import br.com.yourwishismycommand.application.strategy.CreateProfessionalProfile;
import br.com.yourwishismycommand.application.usecases.*;
import br.com.yourwishismycommand.domain.repositories.OrderRepository;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;
import br.com.yourwishismycommand.domain.repositories.UserRepository;
import br.com.yourwishismycommand.infra.repositoy.OrderRepositoryImpl;
import br.com.yourwishismycommand.infra.repositoy.ProfileRepositoryImpl;
import br.com.yourwishismycommand.infra.repositoy.UserRepositoryImpl;
import br.com.yourwishismycommand.infra.repositoy.jpa.OrderRepositoryJpa;
import br.com.yourwishismycommand.infra.repositoy.jpa.ProfileRepositoryJpa;
import br.com.yourwishismycommand.infra.security.JwtFilter;
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
    public ProfileRepository profileRepository(ProfileRepositoryJpa profileRepositoryJpa) {
        return new ProfileRepositoryImpl(
                profileRepositoryJpa
        );
    }
    @Bean
    public CreateProfileUseCase createProfileUseCase(
            CreateClientProfile createClientProfile,
            CreateProfessionalProfile createProfessionalProfile,
            ProfileRepository profileRepository
    ) {
        return new CreateProfileUseCaseImpl(createClientProfile, createProfessionalProfile, profileRepository);
    }
    @Bean
    public CreateClientProfile createClientProfile(AnnotationBasedValidator annotationBasedValidator, ProfileRepository profileRepository) {
        return new CreateClientProfile(
                annotationBasedValidator,
                profileRepository
        );
    }
    @Bean
    public CreateProfessionalProfile createProfessionalProfile(AnnotationBasedValidator annotationBasedValidator, ProfileRepository profileRepository) {
        return new CreateProfessionalProfile(annotationBasedValidator, profileRepository);
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
            JwtManagerService jwtManagerService
    ) {
        return new AuthenticationServiceImpl(authenticationManager, jwtManagerService);
    }
    @Bean
    public RoleDecisionMakerService roleDecisionMakerService(ProfileRepository profileRepository)  {
        return new RoleDecisionMakerServiceImpl(profileRepository);
    }
    @Bean
    public ExtractAllProfilesUseCase extractAllProfilesUseCase(ProfileRepository profileRepository) {
        return new ExtractAllProfilesUseCaseImpl(profileRepository);
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
    @Bean
    public JwtFilter jwtFilter(JwtManagerService jwtManagerService) {
        return new JwtFilter(jwtManagerService);
    }
    @Bean
    public OpenNewOrderUseCase openNewOrderUseCase(AnnotationBasedValidator annotationBasedValidator, UserRepository userRepository, OrderRepository orderRepository) {
        return new OpenNewOrderUseCaseImpl(annotationBasedValidator, userRepository, orderRepository);
    }
    @Bean
    public OrderRepository orderRepository(OrderRepositoryJpa orderRepositoryJpa) {
        return new OrderRepositoryImpl(orderRepositoryJpa);
    }
}
