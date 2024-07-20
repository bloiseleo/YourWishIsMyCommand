package br.com.yourwishismycommand.application.services;

import br.com.yourwishismycommand.application.dtos.UserWithRoleDTO;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;

public interface JwtManagerService {
    String generateJwtToken(User user, UserRole role);
    UserWithRoleDTO decodeJwt(String token);
}
