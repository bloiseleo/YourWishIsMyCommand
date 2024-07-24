package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.inbound.NewOrderDTO;
import br.com.yourwishismycommand.domain.entities.Order;
import br.com.yourwishismycommand.domain.entities.User;

public interface OpenNewOrderUseCase {
    Order open(NewOrderDTO orderDTO, User user);
}
