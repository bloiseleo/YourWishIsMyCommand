package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.outbound.LoginUserDTO;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;

public interface AuhtenticateUserUseCase {
    String authenticate(LoginUserDTO loginUserDTO) throws InvalidCredentialsException;
}
