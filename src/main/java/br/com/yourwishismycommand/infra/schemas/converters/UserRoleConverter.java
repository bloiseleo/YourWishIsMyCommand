package br.com.yourwishismycommand.infra.schemas.converters;

import br.com.yourwishismycommand.domain.entities.UserRole;
import jakarta.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserRole role) {
        return role.ordinal();
    }

    @Override
    public UserRole convertToEntityAttribute(Integer integer) {
        for(var role: UserRole.values()) {
            if(role.ordinal() == integer) {
                return role;
            }
        }
        throw new IllegalArgumentException("Could not convert to integer to UserRole");
    }
}
