package br.com.yourwishismycommand.domain.repositories;

import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    boolean userExists(User user);
    Optional<User> findByEmail(Email email);
    Optional<User> findById(int id);
}
