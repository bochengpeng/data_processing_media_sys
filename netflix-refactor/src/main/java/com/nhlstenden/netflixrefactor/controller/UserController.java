package com.nhlstenden.netflixrefactor.controller;

import com.nhlstenden.netflixrefactor.models.User;
import com.nhlstenden.netflixrefactor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User created = userService.registerUser(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/activate/{token}")
    public ResponseEntity<String> activateUser(@PathVariable String token) {
        userService.activateUser(token);
        return ResponseEntity.ok("User activated successfully");
    }

    @PostMapping("/login/failure")
    public ResponseEntity<Void> incrementFailedLogin(@RequestParam String email) {
        userService.incrementFailedLogin(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-request")
    public ResponseEntity<String> requestReset(@RequestParam String email) {
        userService.requestPasswordReset(email);
        return ResponseEntity.ok("Reset link sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }
}
