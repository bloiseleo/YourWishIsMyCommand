package br.com.yourwishismycommand.application.dtos.inbound;

import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;

public interface RegisterProfileDTO {
    RegisterAddressDTO address();
    UserRole role();
    String tradingName();
    Profile toDomain(User user);
}
