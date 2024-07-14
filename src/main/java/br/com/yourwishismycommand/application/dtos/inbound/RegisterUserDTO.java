package br.com.yourwishismycommand.application.dtos.inbound;

public interface RegisterUserDTO {
    String email();
    String name();
    String password();
    String role();
}
