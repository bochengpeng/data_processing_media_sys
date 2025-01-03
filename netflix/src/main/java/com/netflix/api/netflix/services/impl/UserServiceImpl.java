package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.LoginDto;
import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.ResourceAlreadyExistsException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(LoginDto loginDto) throws ResourceAlreadyExistsException
    {
        if (this.userRepository.existsByEmail(loginDto.getEmail()))
        {
            throw new ResourceAlreadyExistsException("Email is already in use");
        }

        User user = mapToEntity(loginDto);
        user.setActivated(false); // Newly created user should not be activated yet
        user.setFailedLoginAttempts(0); // Default value
        user.setAccountLockUntil(null); // No lock on new user accounts
        User newUser = this.userRepository.save(user);

        return mapToDto(newUser);
    }


    @Override
    public UserDto getUserById(int userId) throws UserNotFoundException
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) throws UserNotFoundException
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for update"));

        user.setEmail(userDto.getEmail());
        user.setActivated(userDto.isActivated());

        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public void deleteUser(int userId) throws UserNotFoundException
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for delete"));
        userRepository.delete(user);
    }

    @Override
    public void activateUser(String token)
    {
        User user = userRepository.findByActivationToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired activation token"));
        user.setActivated(true);
        user.setActivationToken(null); // Clear the token after activation
        userRepository.save(user);
    }


    private UserDto mapToDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setActivated(user.isActivated());
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setAccountLockUntil(user.getAccountLockUntil());
        userDto.setFailedLoginAttempt(user.getFailedLoginAttempts());
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

