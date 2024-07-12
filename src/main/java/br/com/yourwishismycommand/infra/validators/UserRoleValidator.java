package br.com.yourwishismycommand.infra.validators;

import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.domain.exceptions.InvalidRoleException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserRoleValidator implements ConstraintValidator<UserRoleValid, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            UserRole.validateRole(s);
            return true;
        } catch (InvalidRoleException exception) {
            return false;
        }
    }
}
