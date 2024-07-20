package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;

public abstract class AbstractUseCase {
    protected final AnnotationBasedValidator annotationBasedValidator;
    public AbstractUseCase(
            AnnotationBasedValidator annotationBasedValidator
    ) {
        this.annotationBasedValidator = annotationBasedValidator;
    }
}
