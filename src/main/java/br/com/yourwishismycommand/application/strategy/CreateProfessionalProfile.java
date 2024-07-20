package br.com.yourwishismycommand.application.strategy;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterProfileDTO;
import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.application.usecases.AbstractUseCase;
import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;

import java.util.List;

public class CreateProfessionalProfile extends AbstractUseCase implements CreateProfileStrategy {
    private final ProfileRepository profileRepository;
    public CreateProfessionalProfile(AnnotationBasedValidator annotationBasedValidator, ProfileRepository profileRepository) {
        super(annotationBasedValidator);
        this.profileRepository = profileRepository;
    }
    @Override
    public Profile createProfile(RegisterProfileDTO registerProfileDTO, User user) {
        annotationBasedValidator.validateCreate(registerProfileDTO);
        if(registerProfileDTO.tradingName() == null) {
            throw new ApplicationValidationException(List.of("Trading name must be fullfilled"));
        }
        if(registerProfileDTO.tradingName().isEmpty()) {
            throw new ApplicationValidationException(List.of("Trading name must be fullfilled"));
        }
        if(registerProfileDTO.tradingName().length() < 3) {
            throw new ApplicationValidationException(List.of("Trading name must have more than 3 characters"));
        }
        return profileRepository.save(registerProfileDTO.toDomain(user));
    }
}
