package br.com.yourwishismycommand.domain.repositories;

import br.com.yourwishismycommand.domain.entities.User;

public interface UserRepository {
    User save(User user);
    boolean userExists(User user);
}
