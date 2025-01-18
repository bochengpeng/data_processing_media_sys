package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.*;
import com.netflix.api.netflix.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/netflix/user")
public class AuthController
{
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request)
    {
        this.authService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email)
    {
        this.authService.initiatePasswordReset(email);
        return ResponseEntity.ok("Password reset email sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequest request)
    {
        this.authService.resetPassword(token, request.getNewPassword());
        return ResponseEntity.ok("Password has been reset successfully.");
    }
}