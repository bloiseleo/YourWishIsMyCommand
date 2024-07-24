package br.com.yourwishismycommand.infra.repositoy.jpa;

import br.com.yourwishismycommand.infra.schemas.OrderSchema;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepositoryJpa extends CrudRepository<OrderSchema, Integer> {
}
