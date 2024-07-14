package br.com.yourwishismycommand.infra.services;

import br.com.yourwishismycommand.application.services.AuthenticationService;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public boolean authenticate(Email email, String password) throws InvalidCredentialsException {
        try {
            var authObj = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email.toString(), password));
            return authObj.isAuthenticated();
        } catch (AuthenticationException authenticationException) {
            throw new InvalidCredentialsException(authenticationException);
        }
    }
}
