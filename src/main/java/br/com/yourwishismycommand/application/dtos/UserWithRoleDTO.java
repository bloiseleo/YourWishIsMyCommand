package br.com.yourwishismycommand.application.dtos;

import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;

public record UserWithRoleDTO(
        User user,
        UserRole role
) {
}
