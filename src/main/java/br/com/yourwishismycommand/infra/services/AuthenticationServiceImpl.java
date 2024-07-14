package br.com.yourwishismycommand.infra.services;

import br.com.yourwishismycommand.application.services.AuthenticationService;
import br.com.yourwishismycommand.application.services.JwtManagerService;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;
import br.com.yourwishismycommand.domain.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtManagerService jwtManagerService;
    public AuthenticationServiceImpl(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtManagerService jwtManagerService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtManagerService = jwtManagerService;
    }
    @Override
    public String authenticate(Email email, String password) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email.toString(), password));
            var user = userRepository.findByEmail(email);
            if (user.isEmpty()) throw new InvalidCredentialsException();
            return jwtManagerService.generateJwtToken(user.get());
        } catch (AuthenticationException authenticationException) {
            throw new InvalidCredentialsException(authenticationException);
        }
    }
}
