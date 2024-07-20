package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;

import java.util.List;

public interface ExtractAllProfilesUseCase {
    public List<Profile> profilesOf(User user);
}
