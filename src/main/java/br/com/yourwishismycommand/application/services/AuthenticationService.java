package br.com.yourwishismycommand.application.services;

import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;

public interface AuthenticationService {
    String authenticate(Email email, String password) throws InvalidCredentialsException;
}
