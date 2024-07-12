package br.com.yourwishismycommand.domain.entities;

import br.com.yourwishismycommand.domain.exceptions.InvalidRoleException;

public enum UserRole {
    GUEST,
    CLIENT,
    PROFESSIONAL;

    public static void validateRole(String role) {
        try {
            valueOf(role);
        } catch (IllegalArgumentException exception) {
            throw new InvalidRoleException(role);
        }
    }
}
