package br.com.yourwishismycommand.infra.dtos.outbound;

import br.com.yourwishismycommand.application.dtos.outbound.OutboundProfileDTO;
import br.com.yourwishismycommand.domain.entities.Address;
import br.com.yourwishismycommand.domain.entities.UserRole;

public record OutboundProfileDTOImpl(
        Address address,
        UserRole role,
        String tradingName
) implements OutboundProfileDTO { }
