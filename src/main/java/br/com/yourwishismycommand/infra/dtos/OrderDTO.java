package br.com.yourwishismycommand.infra.dtos;

import br.com.yourwishismycommand.application.dtos.inbound.NewOrderDTO;
import br.com.yourwishismycommand.infra.validators.groups.Create;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrderDTO(
        @NotNull(message = "must be selected", groups = {Create.class})
        @Min(value = 1, message = "must be bigger than 0", groups = {Create.class})
        int professional,
        @NotEmpty(message = "cannot be empty", groups = {Create.class})
        @Size(min = 3, message = "description must have more than 3 characters", groups = {Create.class})
        String description,
        @NotEmpty(message = "cannot be empty", groups = {Create.class})
        @Size(min = 1, message = "one media must be provided", groups = {Create.class})
        List<String> medias
) implements NewOrderDTO { }
