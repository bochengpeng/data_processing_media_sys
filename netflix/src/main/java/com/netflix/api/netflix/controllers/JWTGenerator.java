package com.netflix.api.netflix.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Getter
@Component
public class JWTGenerator
{
    // Expose the key for validation purposes
    private final Key key;

    public JWTGenerator()
    {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a key
    }

    public String generateToken(String subject)
    {
        return Jwts.builder()
                .setSubject(subject)  // Set the subject (e.g., username or user ID)
                .setIssuedAt(new Date()) // Token creation time
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1-hour expiration
                .signWith(this.key) // Sign with the secret key
                .compact(); // Build the token
    }
}

