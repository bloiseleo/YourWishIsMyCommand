package br.com.yourwishismycommand.application.services;

import br.com.yourwishismycommand.domain.entities.User;

public interface JwtManagerService {
    String generateJwtToken(User user);
    User decodeJwt(String token);
}
