package br.com.yourwishismycommand.infra.repositoy;

import br.com.yourwishismycommand.application.services.SchemaEntityAdapter;
import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;
import br.com.yourwishismycommand.infra.repositoy.jpa.ProfileRepositoryJpa;
import br.com.yourwishismycommand.infra.schemas.ProfileSchema;
import br.com.yourwishismycommand.infra.schemas.UserSchema;

public class ProfileRepositoryImpl implements ProfileRepository {
    private final ProfileRepositoryJpa profileRepositoryJpa;
    private final SchemaEntityAdapter<ProfileSchema, Profile> adapter;
    public ProfileRepositoryImpl(
            ProfileRepositoryJpa profileRepositoryJpa,
            SchemaEntityAdapter<ProfileSchema, Profile> adapter) {
        this.profileRepositoryJpa = profileRepositoryJpa;
        this.adapter = adapter;
    }
    @Override
    public Profile save(Profile profile) {
        var schema = ProfileSchema.create(profile);
        var schemaWithid = profileRepositoryJpa.save(schema);
        return adapter.to(schemaWithid);
    }

    @Override
    public boolean userHasProfileOfRole(User user, UserRole role) {
        var schema = UserSchema.from(user);
        return profileRepositoryJpa.existsProfileSchemaByUserAndRole(schema.getId(), role.toString());
    }
}
