package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.*;
import com.netflix.api.netflix.exception.AuthenticationFailedException;
import com.netflix.api.netflix.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/netflix/auth")
public class AuthController
{
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request)
    {
        try
        {
            this.authService.authenticate(request.getEmail(), request.getPassword());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (AuthenticationFailedException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("An unexpected error occurred during login");
        }
    }
}