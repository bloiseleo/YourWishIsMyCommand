package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.dtos.APIBaseResponse;
import br.com.yourwishismycommand.application.dtos.APIValuableResponse;
import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {
    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    @ExceptionHandler({Exception.class})
    public ResponseEntity<APIBaseResponse> handleException(Exception exception) {
        logger.error(
                String.format("%s\nStack Trace -> %s", exception, Arrays.stream(exception.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(
                        Collectors
                                .joining("\n")
                ))
        );
        return ResponseEntity.internalServerError()
                .body(
                        new APIBaseResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "An internal server error occured. Please contact the administrator"
                        )
                );
    }
    @ExceptionHandler({ApplicationValidationException.class})
    public ResponseEntity<APIValuableResponse<List<String>>> handleApplicationException(ApplicationValidationException applicationValidationException) {
        return ResponseEntity.unprocessableEntity().body(
                new APIValuableResponse<>(
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        applicationValidationException.getMessage(),
                        applicationValidationException.getViolationsMessages()
                )
        );
    }
}
