package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.*;
import com.netflix.api.netflix.models.Role;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.RoleRepository;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

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
        authService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        authService.initiatePasswordReset(email);
        return ResponseEntity.ok("Password reset email sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(token, request.getNewPassword());
        return ResponseEntity.ok("Password has been reset successfully.");
    }

}