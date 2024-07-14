package br.com.yourwishismycommand.infra.services;

import br.com.yourwishismycommand.application.services.JwtManagerService;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class JwtServiceImpl implements JwtManagerService {
    private final String secret;
    private final String issuer;
    public JwtServiceImpl(
            String secret,
            String issuer
    ) {
        this.secret = secret;
        this.issuer = issuer;
    }
    private Instant generateExpireAt() {
        return Instant.now().plus(1, ChronoUnit.DAYS);
    }
    @Override
    public String generateJwtToken(User user) {
        var algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(generateExpireAt())
                .withSubject(user.getEmail().toString())
                .withClaim("role", user.getRole().name())
                .withClaim("name", user.getName())
                .sign(algorithm);
    }
    @Override
    public User decodeJwt(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            var verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            var decoded = verifier.verify(token);
            return new User(
                    decoded.getClaim("name").asString(),
                    new Email(decoded.getSubject()),
                    UserRole.valueOf(decoded.getClaim("role").asString()),
                    ""
            );
        } catch (JWTVerificationException jwtVerificationException) {
            throw new RuntimeException(jwtVerificationException);
        }
    }
}
