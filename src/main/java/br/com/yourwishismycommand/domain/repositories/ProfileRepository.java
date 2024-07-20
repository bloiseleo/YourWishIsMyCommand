package br.com.yourwishismycommand.domain.repositories;

import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;


public interface ProfileRepository {
    Profile save(Profile profile);
    boolean userHasProfileOfRole(User user, UserRole role);
}
