package com.example.Ecommerce.security;

import com.example.Ecommerce.payload.UserResponseDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${SECRET}")
    private String secret;

    private Key signingKey;

    @PostConstruct
    public void init() {
        signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserResponseDTO user) {
        String email = user.getEmail();

        Date currentDate = new Date();
        Date expiryDate = new Date(System.currentTimeMillis() + 86400000); // 1 day

        return Jwts.builder()
                .setSubject(email)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("isSeller", user.getIsSeller())
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Token Issue: " + e.getMessage());
        }
    }
}
