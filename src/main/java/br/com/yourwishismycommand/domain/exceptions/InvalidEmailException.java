package br.com.yourwishismycommand.domain.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super(String.format("Email %s is not valid", email));
    }
    public InvalidEmailException() {
        super("Email is not valid");
    }
}
