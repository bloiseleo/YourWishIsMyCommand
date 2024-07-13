package br.com.yourwishismycommand.infra.repositoy.jpa;

import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.infra.schemas.UserSchema;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryJpa extends CrudRepository<UserSchema, Integer> {
    boolean existsByEmail(Email email);
}
