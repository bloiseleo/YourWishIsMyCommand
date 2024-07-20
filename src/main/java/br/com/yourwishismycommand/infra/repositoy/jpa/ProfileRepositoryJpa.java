package br.com.yourwishismycommand.infra.repositoy.jpa;

import br.com.yourwishismycommand.infra.schemas.ProfileSchema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepositoryJpa extends CrudRepository<ProfileSchema, Integer> {
    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM profiles p WHERE p.user_id = :userId AND p.role = CAST(:role AS user_roles)) THEN TRUE ELSE FALSE END", nativeQuery = true)
    boolean existsProfileSchemaByUserAndRole(Integer userId, String role);
}
