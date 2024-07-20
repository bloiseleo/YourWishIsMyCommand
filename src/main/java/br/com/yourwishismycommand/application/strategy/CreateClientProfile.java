package br.com.yourwishismycommand.application.strategy;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterProfileDTO;
import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.application.usecases.AbstractUseCase;
import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;

public class CreateClientProfile extends AbstractUseCase implements CreateProfileStrategy {
    private final ProfileRepository repository;
    public CreateClientProfile(
            AnnotationBasedValidator annotationBasedValidator,
            ProfileRepository profileRepository) {
        super(annotationBasedValidator);
        this.repository = profileRepository;
    }
    @Override
    public Profile createProfile(RegisterProfileDTO registerProfileDTO, User user) {
        annotationBasedValidator.validateCreate(registerProfileDTO);
        return this.repository.save(registerProfileDTO.toDomain(user));
    }
}
