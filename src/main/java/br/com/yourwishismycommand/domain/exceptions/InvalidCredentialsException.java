package br.com.yourwishismycommand.domain.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(Exception exception) {
        super(exception.getMessage());
    }
    public InvalidCredentialsException() {
        super("Invalid Credentials");
    }
}
