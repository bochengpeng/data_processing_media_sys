package org.example.dataprocessing.controller;

import org.example.dataprocessing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController
{

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user)
    {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully. Activation email sent.");
    }

    @GetMapping("/activate/{email}")
    public ResponseEntity<String> activateAccount(@PathVariable String email)
    {
        userService.activateAccount(email);
        return ResponseEntity.ok("Account activated successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest)
    {
        boolean success = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (success)
        {
            return ResponseEntity.ok("Login successful.");
        }
        else
        {
            return ResponseEntity.status(401).body("Login failed. Account may be blocked or credentials are incorrect.");
        }
    }
}

