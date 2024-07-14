package br.com.yourwishismycommand.infra.repositoy;

import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.repositories.UserRepository;
import br.com.yourwishismycommand.infra.repositoy.jpa.UserRepositoryJpa;
import br.com.yourwishismycommand.infra.schemas.UserSchema;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserRepositoryJpa userRepositoryJpa;
    @Override
    public User save(User user) {
        return userRepositoryJpa.save(UserSchema.from(user))
                .toDomain();
    }
    @Override
    public boolean userExists(User user) {
        return userRepositoryJpa.existsByEmail(user.getEmail());
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        var optionalSchema = userRepositoryJpa.findUserSchemaByEmail(email);
        if(optionalSchema.isEmpty()) return Optional.empty();
        return Optional.of(optionalSchema.get().toDomain());
    }
}
