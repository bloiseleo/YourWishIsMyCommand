package br.com.yourwishismycommand.infra.dtos;

import br.com.yourwishismycommand.application.dtos.inbound.RegisterUserDTO;
import br.com.yourwishismycommand.application.dtos.outbound.LoginUserDTO;
import br.com.yourwishismycommand.infra.validators.groups.Create;
import br.com.yourwishismycommand.infra.validators.groups.Access;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotNull
        @NotEmpty(groups = {Create.class})
        @Size(min = 3, groups = {Create.class})
        String name,
        @NotEmpty(groups = {Create.class, Access.class})
        String password,
        @NotEmpty(groups = {Create.class, Access.class})
        @Email(groups = {Create.class})
        String email,
        @NotEmpty(groups = {Create.class})
        String role
) implements RegisterUserDTO, LoginUserDTO { }
