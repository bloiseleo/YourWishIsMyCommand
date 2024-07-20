package br.com.yourwishismycommand.infra.services;

import br.com.yourwishismycommand.application.dtos.UserWithRoleDTO;
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
    private Instant generateExpireAt(UserRole role) {
        if (role == UserRole.GUEST) {
            return Instant.now().plus(5, ChronoUnit.MINUTES);
        }
        return Instant.now().plus(1, ChronoUnit.DAYS);
    }
    @Override
    public String generateJwtToken(User user, UserRole role) {
        var algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(generateExpireAt(role))
                .withSubject(user.getEmail().toString())
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("role", role.name())
                .sign(algorithm);
    }
    @Override
    public UserWithRoleDTO decodeJwt(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            var verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            var decoded = verifier.verify(token);
            var user = new User(
                    decoded.getClaim("id").asInt(),
                    decoded.getClaim("name").asString(),
                    new Email(decoded.getSubject()),
                    ""
            );
            var role = decoded.getClaim("role").asString();
            return new UserWithRoleDTO(user, UserRole.valueOf(role));
        } catch (JWTVerificationException jwtVerificationException) {
            throw new RuntimeException(jwtVerificationException);
        }
    }
}
