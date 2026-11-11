package io.github.pedidosapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;

public class JwtService {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final long validadeMs;

    public JwtService(String segredo, long validadeMs) {
        this.algorithm = Algorithm.HMAC256(segredo);
        this.validadeMs = validadeMs;
        this.verifier = JWT.require(algorithm).build();
    }

    public String gerarToken(String username){
        Instant agora = Instant.now();

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(agora)
                .withExpiresAt(agora.plusMillis(validadeMs))
                .sign(algorithm);
    }

    public String extrairUsername(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();

    }

    public boolean tokenValido(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
