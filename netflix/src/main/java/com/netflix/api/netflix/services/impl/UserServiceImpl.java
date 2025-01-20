package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.LoginDto;
import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.ResourceAlreadyExistsException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDto createUser(LoginDto loginDto) throws ResourceAlreadyExistsException
    {
        if (this.userRepository.existsByEmail(loginDto.getEmail()))
        {
            throw new ResourceAlreadyExistsException("Email is already in use");
        }

        // Validate email format using regular expression
        String email = loginDto.getEmail();
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";  // Regex to validate email

        if (!email.matches(emailRegex))
        {
            throw new IllegalArgumentException("Invalid email format");
        }

        User user = mapToEntity(loginDto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(loginDto.getPassword());
        user.setPassword(hashedPassword);
        user.setActivated(false); // Newly created user should not be activated yet
        user.setFailedLoginAttempts(0); // Default value
        user.setAccountLockUntil(null); // No lock on new user accounts
        user.setCreatedAt(LocalDateTime.now());
        User newUser = this.userRepository.save(user);

        return mapToDto(newUser);
    }

    @Override
    public UserDto getUserById(int userId) throws UserNotFoundException
    {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return mapToDto(user);
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto, int userId) throws UserNotFoundException
    {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for update"));

        user.setEmail(userDto.getEmail());
        user.setActivated(userDto.isActivated());

        User updatedUser = this.userRepository.save(user);

        return mapToDto(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(int userId) throws UserNotFoundException
    {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for delete"));
        this.userRepository.delete(user);
    }

    @Transactional
    @Override
    public void activateUser(String token)
    {
        User user = this.userRepository.findByActivationToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired activation token"));
        user.setActivated(true);
        user.setActivationToken(null); // Clear the token after activation
        this.userRepository.save(user);
    }

    private UserDto mapToDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setActivated(user.isActivated());
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setAccountLockUntil(user.getAccountLockUntil());
        userDto.setFailedLoginAttempt(user.getFailedLoginAttempts());
        userDto.setUsername(user.getUsername());

        return userDto;
    }

    private User mapToEntity(LoginDto loginDto)
    {
        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword(loginDto.getPassword());
        user.setUsername(loginDto.getUsername());

        return user;
    }
}