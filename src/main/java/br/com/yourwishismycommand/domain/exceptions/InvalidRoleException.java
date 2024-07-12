package br.com.yourwishismycommand.domain.exceptions;

import br.com.yourwishismycommand.domain.entities.UserRole;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String role) {
        super(message(role));
    }
    private static String message(String role) {
        var validRules = Arrays.stream(UserRole.values())
                .map(UserRole::toString)
                .collect(Collectors.joining(", "));
        var message = String.format("The valid roles are: %s", validRules);
        if(role == null) return message;
        return String.format("%s. You give %s", role);
    }
}
