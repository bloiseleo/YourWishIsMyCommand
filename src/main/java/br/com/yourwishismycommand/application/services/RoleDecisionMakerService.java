package br.com.yourwishismycommand.application.services;

import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;

public interface RoleDecisionMakerService {
    public UserRole decide(User user);
}
