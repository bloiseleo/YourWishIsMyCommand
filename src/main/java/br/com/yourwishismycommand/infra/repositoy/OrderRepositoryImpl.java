package br.com.yourwishismycommand.infra.repositoy;

import br.com.yourwishismycommand.domain.entities.Order;
import br.com.yourwishismycommand.domain.repositories.OrderRepository;
import br.com.yourwishismycommand.infra.repositoy.jpa.OrderRepositoryJpa;

public class OrderRepositoryImpl implements OrderRepository {
    private final OrderRepositoryJpa orderRepositoryJpa;
    public OrderRepositoryImpl(OrderRepositoryJpa orderRepositoryJpa) {
        this.orderRepositoryJpa = orderRepositoryJpa;
    }
    @Override
    public Order save(Order order) {
        return null;
    }
}
