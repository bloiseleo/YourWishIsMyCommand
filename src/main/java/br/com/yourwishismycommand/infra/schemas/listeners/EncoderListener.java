package br.com.yourwishismycommand.infra.schemas.listeners;

import br.com.yourwishismycommand.infra.schemas.UserSchema;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderListener {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PrePersist
    public void encodePassword(UserSchema userSchema) {
        userSchema.setPassword(
                passwordEncoder.encode(userSchema.getPassword())
        );
    }
}
