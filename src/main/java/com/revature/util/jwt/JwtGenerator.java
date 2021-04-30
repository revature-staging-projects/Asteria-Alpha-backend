package com.revature.util.jwt;

import com.revature.dto.PrincipalDTO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class JwtGenerator {

    private final JwtConfig config;

    @Autowired
    public JwtGenerator(final JwtConfig config) {
        this.config = config;
    }


    public String generateJwt(final PrincipalDTO subject) {
        final long now = System.currentTimeMillis();
        return Jwts.builder()
                .setId(String.valueOf(subject.getId()))
                .setSubject(subject.getUsername())
                .setIssuer("Asteria-Alpha")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + (60 * 60 * 1000 * 4)))
                .signWith(config.getSignatureAlgorithm(), config.getSigningKey())
                .compact();
    }

}
