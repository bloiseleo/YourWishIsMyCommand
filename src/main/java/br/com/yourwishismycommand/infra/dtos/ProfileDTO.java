package br.com.yourwishismycommand.infra.dtos;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterProfileDTO;
import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.infra.validators.groups.Create;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ProfileDTO(
        @NotNull(groups = {Create.class})
        UserRole role,
        String tradingName,
        @Valid
        @NotNull(groups = {Create.class})
        AddressDTO address
) implements RegisterProfileDTO {
    public Profile toDomain(User user) {
        return new Profile(
            user,
            address().toDomain(),
            tradingName(),
            role()
        );
    }
}
