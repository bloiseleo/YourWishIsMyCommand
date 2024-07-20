package br.com.yourwishismycommand.infra.services;

import br.com.yourwishismycommand.application.services.AuthenticationService;
import br.com.yourwishismycommand.application.services.JwtManagerService;
import br.com.yourwishismycommand.application.services.RoleDecisionMakerService;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;
import br.com.yourwishismycommand.infra.security.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtManagerService jwtManagerService;
    public AuthenticationServiceImpl(
            AuthenticationManager authenticationManager,
            JwtManagerService jwtManagerService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtManagerService = jwtManagerService;
    }
    @Override
    public String authenticate(Email email, String password) throws InvalidCredentialsException {
        try {
            var authenticationObject = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email.toString(), password));
            var details = ((UserDetailsImpl) authenticationObject.getPrincipal());
            return jwtManagerService.generateJwtToken(details.getUser(), authorityToRole(details));
        } catch (AuthenticationException authenticationException) {
            throw new InvalidCredentialsException(authenticationException);
        }
    }
    private UserRole authorityToRole(UserDetails userDetails) {
        var authorities = userDetails.getAuthorities();
        if(authorities.size() > 1) {
            throw new RuntimeException("User has more Authorities than allowed");
        }
        var authTreated = authorities.stream().map(GrantedAuthority::getAuthority).map(UserRole::valueOf).toArray();
        return (UserRole) authTreated[0];
    }
}
