package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterProfileDTO;
import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;

public interface CreateProfileUseCase {
    Profile createProfile(RegisterProfileDTO registerProfileDTO, User user);
}
