package br.com.yourwishismycommand.domain.exceptions;

public class InvalidClientAndProfessional extends RuntimeException {
    public InvalidClientAndProfessional() {
        super("Client cannot be the same professional");
    }
}
