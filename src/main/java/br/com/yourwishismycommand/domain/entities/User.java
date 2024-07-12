package br.com.yourwishismycommand.domain.entities;

public class User {
    private String name;
    private UserRole role;
    private Email email;
    public User(
            String name,
            Email email,
            UserRole role
    ) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
    private String getName() {
        return name;
    }
    private UserRole getRole() {
        return role;
    }
    private Email getEmail() {
        return email;
    }
}
