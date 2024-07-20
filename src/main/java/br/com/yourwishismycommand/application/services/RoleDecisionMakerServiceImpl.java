package br.com.yourwishismycommand.application.services;

import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;

public class RoleDecisionMakerServiceImpl implements RoleDecisionMakerService{
    private final ProfileRepository profileRepository;
    public RoleDecisionMakerServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    @Override
    public UserRole decide(User user) {
        var profiles = profileRepository.allProfilesOf(user);
        if(profiles.size() != 1) {
            return UserRole.GUEST;
        }
        return profiles.get(0).getUserRole();
    }
}
