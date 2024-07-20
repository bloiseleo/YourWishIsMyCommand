package br.com.yourwishismycommand.infra.repositoy;

import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;
import br.com.yourwishismycommand.infra.repositoy.jpa.ProfileRepositoryJpa;
import br.com.yourwishismycommand.infra.schemas.ProfileSchema;
import br.com.yourwishismycommand.infra.schemas.UserSchema;

import java.util.List;

public class ProfileRepositoryImpl implements ProfileRepository {
    private final ProfileRepositoryJpa profileRepositoryJpa;
    public ProfileRepositoryImpl(
            ProfileRepositoryJpa profileRepositoryJpa) {
        this.profileRepositoryJpa = profileRepositoryJpa;
    }
    @Override
    public Profile save(Profile profile) {
        var schema = ProfileSchema.create(profile);
        var schemaWithid = profileRepositoryJpa.save(schema);
        return schemaWithid.toDomain();
    }
    @Override
    public boolean userHasProfileOfRole(User user, UserRole role) {
        var schema = UserSchema.from(user);
        return profileRepositoryJpa.existsProfileSchemaByUserAndRole(schema.getId(), role.toString());
    }
    @Override
    public List<Profile> allProfilesOf(User user) {
        return profileRepositoryJpa
                .findAllByUser(UserSchema.from(user))
                .stream()
                .map(ProfileSchema::toDomain)
                .toList();
    }
}
