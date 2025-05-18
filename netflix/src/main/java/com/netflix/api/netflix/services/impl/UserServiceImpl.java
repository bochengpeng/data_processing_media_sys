package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SignUpDto;
import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.ResourceAlreadyExistsException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repositories.UserRepository;
import com.netflix.api.netflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

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
    public UserDto createUser(SignUpDto signUpDto) throws ResourceAlreadyExistsException
    {
        try
        {
            if (this.userRepository.existsByEmail(signUpDto.getEmail()))
            {
                throw new ResourceAlreadyExistsException("Email is already in use");
            }

            User user = mapToEntity(signUpDto);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(signUpDto.getPassword());
            user.setPassword(hashedPassword);
            user.setActivationToken(UUID.randomUUID().toString());
            user.setActivationTokenExpiry(LocalDateTime.now().plusDays(1));
            user.setActivated(false);
            user.setFailedLoginAttempts(0);
            user.setAccountLockUntil(null);
            user.setCreatedAt(LocalDateTime.now());

            User newUser = this.userRepository.save(user);

            return mapToDto(newUser);
        } catch (ResourceAlreadyExistsException | IllegalArgumentException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create user", e);
        }
    }

    @Override
    public UserDto getUserById(int userId) throws UserNotFoundException
    {
        try
        {
            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            return mapToDto(user);
        } catch (UserNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to get user by ID", e);
        }
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto, int userId) throws UserNotFoundException
    {
        try
        {
            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found for update"));

            user.setEmail(userDto.getEmail());
            user.setActivated(userDto.isActivated());

            User updatedUser = this.userRepository.save(user);

            return mapToDto(updatedUser);
        } catch (UserNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update user", e);
        }
    }

    @Transactional
    @Override
    public void deleteUser(int userId) throws UserNotFoundException
    {
        try
        {
            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found for delete"));
            this.userRepository.delete(user);
        } catch (UserNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    @Transactional
    @Override
    public void activateUser(String token)
    {
        try
        {
            User user = this.userRepository.findByActivationToken(token)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid or expired activation token"));

            if (user.getActivationTokenExpiry().isBefore(LocalDateTime.now()))
            {
                throw new RuntimeException("Activation token expired");
            }

            user.setActivated(true);
            user.setActivationToken(null);
            this.userRepository.save(user);
        } catch (RuntimeException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to activate user", e);
        }
    }

    @Transactional
    @Override
    public void requestPasswordReset(String email) throws UserNotFoundException
    {
        try
        {
            User user = this.userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("Invalid email or password"));
            user.setResetToken(UUID.randomUUID().toString());
            user.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
            this.userRepository.save(user);
        } catch (UserNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to request password reset", e);
        }
    }

    @Transactional
    @Override
    public void resetPassword(String token, String newPassword)
    {
        try
        {
            User user = this.userRepository.findByResetToken(token)
                    .orElseThrow(() -> new RuntimeException("Invalid reset token"));

            if (user.getResetTokenExpiry().isBefore(LocalDateTime.now()))
            {
                throw new RuntimeException("Reset token expired");
            }

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(hashedPassword);
            user.setResetToken(null);
            user.setResetTokenExpiry(null);
            this.userRepository.save(user);
        } catch (RuntimeException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to reset password", e);
        }
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

    private User mapToEntity(SignUpDto signUpDto)
    {
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        user.setUsername(signUpDto.getUsername());
        return user;
    }
}