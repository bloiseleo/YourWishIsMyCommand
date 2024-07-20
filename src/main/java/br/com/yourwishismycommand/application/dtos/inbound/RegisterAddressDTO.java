package br.com.yourwishismycommand.application.dtos.inbound;

import br.com.yourwishismycommand.domain.entities.Address;

public interface RegisterAddressDTO {
    String address();
    int addressNumber();
    String complement();
    String addressCode();
    String city();
    String state();
    Address toDomain();
}
