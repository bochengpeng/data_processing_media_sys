package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.*;
import com.netflix.api.netflix.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> login(@RequestBody LoginRequest request)
    {
        this.authService.authenticate(request.getEmail(), request.getPassword());

        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email)
    {
        this.authService.initiatePasswordReset(email);

        return new ResponseEntity<>("Password reset email sent.", HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request)
    {
        this.authService.resetPassword(request.getToken(), request.getNewPassword());

        return new ResponseEntity<>("Password has been reset successfully.", HttpStatus.OK);
    }
}