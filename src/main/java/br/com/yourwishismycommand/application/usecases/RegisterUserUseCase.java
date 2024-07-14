package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterUserDTO;
import br.com.yourwishismycommand.domain.entities.User;

public interface RegisterUserUseCase {
    User registerUser(RegisterUserDTO registerUserDTO);
}
