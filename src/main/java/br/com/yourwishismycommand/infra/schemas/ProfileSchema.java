package br.com.yourwishismycommand.infra.schemas;

import br.com.yourwishismycommand.domain.entities.Profile;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.infra.schemas.embeddables.AddressSchema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@Getter
@Setter
public class ProfileSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profiles_id_seq")
    @SequenceGenerator(name = "profiles_id_seq", allocationSize = 1)
    private int id;
    @Embedded
    private AddressSchema addressSchema;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
    @Column(name = "trading_name")
    private String tradingName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserSchema user;
    public ProfileSchema() {}
    private ProfileSchema(
            User user,
        AddressSchema addressSchema,
        UserRole role,
        String tradingName
    ) {
        this.addressSchema = addressSchema;
        this.role = role;
        this.tradingName = tradingName;
        this.user = UserSchema.from(user);
    }
    public static ProfileSchema create(Profile profile) {
        return new ProfileSchema(
                profile.getUser(),
            AddressSchema.create(profile.getAddress()),
                profile.getUserRole(),
                profile.getTradingName()
        );
    }
    public Profile toDomain() {
        return new Profile(
                user.toDomain(),
                addressSchema.toDomain(),
                tradingName,
                role
        );
    }
}
