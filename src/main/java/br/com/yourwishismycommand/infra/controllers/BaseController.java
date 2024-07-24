package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.infra.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {
    protected User extractUserFromSecurityContext() {
        var authenticationToken = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        var details = (UserDetailsImpl) authenticationToken.getPrincipal();
        return details.getUser();
    }
}
