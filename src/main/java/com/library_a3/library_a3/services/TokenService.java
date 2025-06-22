package com.library_a3.library_a3.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.library_a3.library_a3.domains.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(Credentials credentias, String userId, String organizationId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                    .create()
                    .withIssuer("auth-api")
                    .withClaim("authority", credentias.getRole().toString())
                    .withClaim("credentialId", credentias.getId())
                    .withClaim("userId", userId)
                    .withClaim("organizationId", organizationId)
                    .withSubject(credentias.getEmail())
                    .withExpiresAt(this.genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException err) {
            throw new RuntimeException("Error while generate token", err);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return ""; // mudar para uma exception
        }
    }
    public Claim getAuthority(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getClaim("authority");
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Error while validate Token");
        }
    }

    public Claim getUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getClaim("userId");
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Error while validate Token");
        }
    }

    public Claim getOrganzationId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token) 
                    .getClaim("organizationId");
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Error while validate Token");
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
