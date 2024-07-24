package br.com.yourwishismycommand.application.usecases;

import br.com.yourwishismycommand.application.dtos.inbound.NewOrderDTO;
import br.com.yourwishismycommand.application.services.AnnotationBasedValidator;
import br.com.yourwishismycommand.domain.entities.Order;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;
import br.com.yourwishismycommand.domain.repositories.OrderRepository;
import br.com.yourwishismycommand.domain.repositories.UserRepository;

import java.util.List;

public class OpenNewOrderUseCaseImpl extends AbstractUseCase implements OpenNewOrderUseCase {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    public OpenNewOrderUseCaseImpl(
            AnnotationBasedValidator annotationBasedValidator,
            UserRepository userRepository,
            OrderRepository orderRepository
    ) {
        super(annotationBasedValidator);
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    public Order open(NewOrderDTO orderDTO, User user) {
        annotationBasedValidator.validateCreate(orderDTO);
        var professionalId = orderDTO.professional();
        var professionalOptional = userRepository.findById(professionalId);
        if(professionalOptional.isEmpty()) {
            throw new ApplicationValidationException(
                    List.of("Professional does not exists anymore")
            );
        }
        var professional = professionalOptional.get();
        var order = new Order(
                user,
                professional,
                orderDTO.description(),
                orderDTO.medias()
        );
        return orderRepository.save(order);
    }
}
