package br.com.yourwishismycommand.domain.exceptions;

import java.util.List;

public class ApplicationValidationException extends RuntimeException {
    private final List<String> violationsMessages;
    public ApplicationValidationException(List<String> violationMessages) {
        super("Data could not be processed due to invalid information");
        this.violationsMessages = violationMessages;
    }
    public List<String> getViolationsMessages() {
        return violationsMessages;
    }
}
