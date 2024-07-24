package br.com.yourwishismycommand.domain.repositories;

import br.com.yourwishismycommand.domain.entities.Order;

public interface OrderRepository {
    Order save(Order order);
}
