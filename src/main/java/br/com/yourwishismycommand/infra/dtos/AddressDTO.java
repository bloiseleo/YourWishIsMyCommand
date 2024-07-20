package br.com.yourwishismycommand.infra.dtos;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterAddressDTO;
import br.com.yourwishismycommand.domain.entities.Address;
import br.com.yourwishismycommand.infra.validators.groups.Create;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AddressDTO(
        @NotEmpty(groups = {Create.class})
        @Size(min = 3,  groups = {Create.class})
        String address,
        @Min(value = 1, groups = {Create.class})
        int addressNumber,
        String complement,
        @NotEmpty(groups = {Create.class})
        @Size(min = 8, max = 8, groups = {Create.class}, message = "addressCode must have 8 characters")
        String addressCode,
        @NotEmpty(groups = {Create.class})
        @Size(min = 3, groups = {Create.class})
        String city,
        @NotEmpty(groups = {Create.class})
        @Size(min = 2, max = 3, groups = {Create.class})
        String state
) implements RegisterAddressDTO {
    @Override
    public Address toDomain() {
        return new Address(
                address,
                addressNumber,
                complement,
                addressCode,
                city,
                state
        );
    }
}
