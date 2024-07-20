package br.com.yourwishismycommand.infra.services;

import br.com.yourwishismycommand.application.services.SchemaEntityAdapter;
import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.infra.schemas.ProfileSchema;

public class SchemaEntityProfileAdapter implements SchemaEntityAdapter<ProfileSchema, Profile> {
    @Override
    public ProfileSchema from(Profile entity) {
        return ProfileSchema.create(entity);
    }
    @Override
    public Profile to(ProfileSchema schema) {
        return new Profile(
                schema.getUser().toDomain(),
                schema.getAddressSchema().toDomain(),
                schema.getTradingName(),
                schema.getRole()
        );
    }
}
