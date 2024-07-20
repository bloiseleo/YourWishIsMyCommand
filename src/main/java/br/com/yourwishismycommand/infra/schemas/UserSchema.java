package br.com.yourwishismycommand.infra.schemas;

import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.infra.schemas.converters.EmailConverter;
import br.com.yourwishismycommand.infra.schemas.listeners.EncoderListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@EntityListeners(EncoderListener.class)
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
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<ProfileSchema> profiles;
    public User toDomain() {
        return new User(
                id,
                name,
                email,
                password
        );
    }
    public static UserSchema from(User user) {
        var schema = new UserSchema();
        if(user.getId() != 0) {
            schema.setId(user.getId());
        }
        schema.setEmail(user.getEmail());
        schema.setName(user.getName());
        schema.setPassword(user.getPassword());
        return schema;
    }
}
