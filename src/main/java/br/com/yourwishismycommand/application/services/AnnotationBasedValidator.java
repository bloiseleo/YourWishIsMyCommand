package br.com.yourwishismycommand.application.services;

public interface AnnotationBasedValidator {
    void validateCreate(Object data);
    void validateAccess(Object data);
}
