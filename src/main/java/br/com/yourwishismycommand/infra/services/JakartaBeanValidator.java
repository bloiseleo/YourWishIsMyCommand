package br.com.yourwishismycommand.infra.services;

import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;
import br.com.yourwishismycommand.infra.validators.groups.Create;
import jakarta.validation.Validator;

public class JakartaBeanValidator implements AnnotationBasedValidator {
    private Validator validator;
    public JakartaBeanValidator(Validator validator) {
        this.validator = validator;
    }
    @Override
    public void validateCreate(Object data) {
        var violations = validator.validate(data, Create.class);
        if(violations.isEmpty()) return;
        var listOfViolations = violations.stream()
                .map(v -> String.format("%s %s", v.getPropertyPath().toString(), v.getMessage()))
                .toList();
        throw new ApplicationValidationException(listOfViolations);
    }
}
