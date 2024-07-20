package br.com.yourwishismycommand.application.dtos.outbound;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterAddressDTO;
import br.com.yourwishismycommand.domain.entities.Address;
import br.com.yourwishismycommand.domain.entities.UserRole;

public interface OutboundProfileDTO {
    UserRole role();
    String tradingName();
    Address address();
}
