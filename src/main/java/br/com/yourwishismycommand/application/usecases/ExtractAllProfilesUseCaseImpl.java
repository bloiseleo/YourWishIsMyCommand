package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;

import java.util.List;

public class ExtractAllProfilesUseCaseImpl implements ExtractAllProfilesUseCase {
    private final ProfileRepository profileRepository;
    public ExtractAllProfilesUseCaseImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    @Override
    public List<Profile> profilesOf(User user) {
        return profileRepository.allProfilesOf(user);
    }
}
