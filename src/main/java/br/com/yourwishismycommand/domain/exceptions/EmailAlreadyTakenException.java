package br.com.yourwishismycommand.domain.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String email) {
        super(String.format("The email %s is already in use", email));
    }
}
