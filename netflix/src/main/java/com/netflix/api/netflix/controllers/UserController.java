package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.UserDto;
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
@RequestMapping("/netflix")
public class UserController
{
    @Autowired
    private UserService userService;

//    @GetMapping
//    public List<User> getAllUsers()
//    {
//        return userRepository.findAll();
//    }


    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "userId") int userId) throws UserNotFoundException
    {
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

//    @PostMapping
//    public User createUser(@RequestBody User user)
//    {
//        return userRepository.save(user);
//    }
//
//    @PutMapping("/{userId}")
//    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userDetails)
//    {
//        return userRepository.findById(userId)
//                .map(user ->
//                {
//                    user.setEmail(userDetails.getEmail());
//                    user.setActivated(userDetails.isActivated());
//                    return ResponseEntity.ok(userRepository.save(user));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable int userId)
//    {
//        return userRepository.findById(userId)
//                .map(user ->
//                {
//                    userRepository.delete(user);
//                    return ResponseEntity.ok().<Void>build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
}