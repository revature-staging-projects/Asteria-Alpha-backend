package com.revature.util.jwt;

import com.revature.Exceptions.InvalidRequestException;
import com.revature.dto.PrincipalDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtParser {

    private final JwtConfig config;

    @Autowired
    public JwtParser(final JwtConfig config) {
        this.config = config;
    }

    public PrincipalDTO parseToken(final String token) {
        final Claims claim = Jwts.parser()
                                .setSigningKey(config.getSigningKey())
                                .parseClaimsJws(token)
                                .getBody();
        final PrincipalDTO subject = new PrincipalDTO();
        subject.setUsername(claim.getSubject());
        return subject;
    }


    public String getTokenFromHeader(final HttpServletRequest request) {
        final String token = request.getHeader("Asteria-token");
        if(token == null || token.isEmpty()) {
            throw new InvalidRequestException("not logged in");
        }
        return token;
    }
}
