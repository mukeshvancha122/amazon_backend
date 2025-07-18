package com.amazon_clone.user_service.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
//creation and validation of the key is taken place in this class
public class Jwtutil {


    private String SECRET_KEY;

    private Long EXPIRATION_TIME;

    private Key signingKey;

    @PostConstruct
    public void init() {
        if(SECRET_KEY.length() < 32) {
            throw new IllegalArgumentException("Secret key must be at least 32 characters long in user-service");
        }
        // Convert secret key string into a signing key using HS256
        signingKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Jwtutil(@Value("${jwt.expiration}") long expirationTime, @Value("${jwt.secret}") String secret_key){
        EXPIRATION_TIME= expirationTime;
        SECRET_KEY= secret_key;
    }

    // Generates JWT token with username as subject
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // payload
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(signingKey, SignatureAlgorithm.HS256) // secure signing
                .compact();
    }

    // Extract username (subject) from the token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Validate token by checking expiration and catching exceptions
    public boolean isTokenValid(String token) {
        try {
            return !extractClaims(token).getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Token is either expired or malformed
        }
    }

    // Helper method to extract all claims from the token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
