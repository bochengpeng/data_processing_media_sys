package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.SignUpDto;
import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.ResourceAlreadyExistsException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/netflix/user")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId)
    {
        try
        {
            UserDto userDto = this.userService.getUserById(userId);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        catch (UserNotFoundException ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpDto signUpDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            UserDto createdUser = this.userService.createUser(signUpDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        catch (ResourceAlreadyExistsException ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestParam String token)
    {
        try
        {
            this.userService.activateUser(token);
            return new ResponseEntity<>("Account activated successfully. You can now log in.", HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Activation failed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int userId, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            UserDto updatedUser = this.userService.updateUser(userDto, userId);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        catch (UserNotFoundException ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable int userId)
    {
        try
        {
            this.userService.deleteUser(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        catch (UserNotFoundException ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reset-request")
    public ResponseEntity<?> requestReset(@RequestParam String email)
    {
        try
        {
            this.userService.requestPasswordReset(email);
            return new ResponseEntity<>("Reset token sent", HttpStatus.OK);
        }
        catch (UserNotFoundException ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody String newPassword)
    {
        try
        {
            this.userService.resetPassword(token, newPassword);
            return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Password reset failed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}