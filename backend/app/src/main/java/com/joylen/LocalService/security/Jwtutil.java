package com.joylen.LocalService.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class Jwtutil {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generatetoken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)   // same key used in generateToken
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
