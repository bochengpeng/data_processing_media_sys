package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.LoginDto;
import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.ResourceAlreadyExistsException;
import com.netflix.api.netflix.exception.SubscriptionNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/user")
public class UserController
{
    @Autowired
    private UserService userService;


    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "userId") int userId) throws UserNotFoundException
    {
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@RequestBody LoginDto loginDto) throws ResourceAlreadyExistsException
    {
        return new ResponseEntity<>(userService.createUser(loginDto), HttpStatus.CREATED);
    }

    @GetMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestParam String token)
    {
        userService.activateUser(token); // Delegate to service
        return ResponseEntity.ok("Account activated successfully. You can now log in.");
    }


    @PutMapping("/{userId}/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable(value = "userId") int userId) throws UserNotFoundException
    {
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") int userId) throws UserNotFoundException, SubscriptionNotFoundException
    {
        userService.deleteUser(userId);
        return new ResponseEntity<>("Subscription deleted successfully", HttpStatus.OK);
    }
}