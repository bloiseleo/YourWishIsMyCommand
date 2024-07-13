package br.com.yourwishismycommand.infra.schemas;

import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.infra.schemas.converters.EmailConverter;
import br.com.yourwishismycommand.infra.schemas.converters.UserRoleConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "email", nullable = false)
    @Convert(converter = EmailConverter.class)
    private Email email;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    public User toDomain() {
        return new User(
                name,
                email,
                role,
                password
        );
    }
    public static UserSchema from(User user) {
        var schema = new UserSchema();
        schema.setEmail(user.getEmail());
        schema.setName(user.getName());
        schema.setRole(user.getRole());
        schema.setPassword(user.getPassword());
        return schema;
    }
}
