package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.dtos.APIBaseResponse;
import br.com.yourwishismycommand.application.usecases.OpenNewOrderUseCase;
import br.com.yourwishismycommand.infra.dtos.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController {
    private final OpenNewOrderUseCase openNewOrderUseCase;
    public OrderController(OpenNewOrderUseCase openNewOrderUseCase) {
        this.openNewOrderUseCase = openNewOrderUseCase;
    }
    @PostMapping
    public APIBaseResponse createOrder(@RequestBody OrderDTO data) {
        var order = openNewOrderUseCase.open(data, extractUserFromSecurityContext());
        return new APIBaseResponse(
                HttpStatus.CREATED.value(),
                String.format("Order %d created successfully", order.getId())
        );
    }
}
