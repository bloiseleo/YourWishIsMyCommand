package br.com.yourwishismycommand.application.strategy;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterProfileDTO;
import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;

public interface CreateProfileStrategy {
    Profile createProfile(RegisterProfileDTO registerProfileDTO, User user);
}
