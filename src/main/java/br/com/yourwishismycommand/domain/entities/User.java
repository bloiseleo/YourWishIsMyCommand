package br.com.yourwishismycommand.domain.entities;

public class User {
    private int id;
    private String name;
    private Email email;
    private String password;
    public User(
            String name,
            Email email,
            String password
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User(
            int id,
            String name,
            Email email,
            String password
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Email getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
