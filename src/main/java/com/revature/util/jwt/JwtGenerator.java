package com.revature.util.jwt;

import com.revature.dto.users.PrincipalDTO;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Class which generates a JWT for a given user.
 */
@Component
public class JwtGenerator {

    private final JwtConfig config;
    private final String issuer = System.getenv("issuer_name");

    @Autowired
    public JwtGenerator(final JwtConfig config) {
        this.config = config;
    }


    public String generateJwt(final PrincipalDTO subject) {
        final long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + (60 * 60 * 1000 * 4)))
                .signWith(config.getSignatureAlgorithm(), config.getSigningKey())
                .compact();
    }

}
