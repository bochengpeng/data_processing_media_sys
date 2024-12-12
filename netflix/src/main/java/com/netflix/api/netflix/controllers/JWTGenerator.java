package com.netflix.api.netflix.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTGenerator {

    private final Key key;

    public JWTGenerator() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a key
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)  // Set the subject (e.g., username or user ID)
                .setIssuedAt(new Date()) // Token creation time
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1-hour expiration
                .signWith(key) // Sign with the secret key
                .compact(); // Build the token
    }

    public Key getKey() {
        return key; // Expose the key for validation purposes
    }
}

