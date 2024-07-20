package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterProfileDTO;
import br.com.yourwishismycommand.application.strategy.CreateClientProfile;
import br.com.yourwishismycommand.application.strategy.CreateProfessionalProfile;
import br.com.yourwishismycommand.application.strategy.CreateProfileStrategy;
import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;

import java.util.List;

public class CreateProfileUseCaseImpl implements CreateProfileUseCase {
    private final CreateProfileStrategy createClient;
    private final CreateProfileStrategy createProfessional;
    private final ProfileRepository profileRepository;
    public CreateProfileUseCaseImpl(
            CreateClientProfile createClient,
            CreateProfessionalProfile createProfessional,
            ProfileRepository profileRepository) {
        this.createClient = createClient;
        this.createProfessional = createProfessional;
        this.profileRepository = profileRepository;
    }
    @Override
    public Profile createProfile(RegisterProfileDTO registerProfileDTO, User user) {
        if(registerProfileDTO.role() == null) {
            throw new ApplicationValidationException(List.of("Role must not be null"));
        }
        if(profileRepository.userHasProfileOfRole(user, registerProfileDTO.role())) {
            throw new ApplicationValidationException(List.of("This user already has a profile with this role"));
        }
        switch (registerProfileDTO.role()) {
            case CLIENT -> {
                return createClient.createProfile(registerProfileDTO, user);
            }
            case PROFESSIONAL -> {
                return createProfessional.createProfile(registerProfileDTO, user);
            }
            default -> {
                throw new ApplicationValidationException(List.of(String.format("%s - This role cannot create a profile", registerProfileDTO.role().toString())));
            }
        }
    }
}
