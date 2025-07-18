package com.amazon_backend.api_gateway.util;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private String SECRET_KEY;
    private Long EXPIRATION_TIME;
    private Key signingKey;

    public JwtUtil(@Value("${jwt.expiration}") long expirationTime, @Value("${jwt.secret}") String secret){
        this.SECRET_KEY=secret;
        this.EXPIRATION_TIME=expirationTime;
    }

    @PostConstruct
    public void init(){
        if(SECRET_KEY.length()<32){
            throw new IllegalArgumentException("Secret key must be at least 32 characters long");
        }
        signingKey= Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(signingKey)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token){
        try{
            return !extractClaims(token).getExpiration().before(new Date());
        }
        catch(ClaimJwtException | IllegalArgumentException e){
            return false;
        }
    }

}
