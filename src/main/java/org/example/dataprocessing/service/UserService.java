package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.User;
import org.example.dataprocessing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Registers a new user
    public User registerUser(User user) {
        // Check if a user with the given email already exists
        userRepository.findByEmail(user.getEmail()).ifPresent(existingUser -> {
            throw new IllegalArgumentException("User with this email already exists");
        });

        // Set default values for new users
        user.setActivated(true);
        user.setFailedLoginAttempts(0);
        user.setAccountLockUntil(null);

        // Save and return the new user
        return userRepository.save(user);
    }

    // Retrieves a user by userId
    public Optional<User> getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    // Updates an existing user
    public User updateUser(User user) {
        // Fetch the existing user and validate
        User existingUser = userRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update user fields
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setActivated(user.getIsActivated());
        existingUser.setFailedLoginAttempts(user.getFailedLoginAttempts());
        existingUser.setAccountLockUntil(user.getAccountLockUntil());
        existingUser.setProfiles(user.getProfiles());
        existingUser.setSubscription(user.getSubscription());

        // Save and return the updated user
        return userRepository.save(existingUser);
    }

    // Activates a user account
    public void activateUser(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setActivated(true);
        userRepository.save(user);
    }

    // Deactivates a user account
    public void deactivateUser(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setActivated(false);
        userRepository.save(user);
    }

    // Handles a failed login attempt
    public void handleFailedLogin(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        int attempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(attempts);

        if (attempts >= 3) { // Lock the account after 3 failed attempts
            user.setAccountLockUntil(LocalDateTime.now().plusMinutes(15));
        }

        userRepository.save(user);
    }

    // Resets failed login attempts and unlocks the account
    public void resetFailedLoginAttempts(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFailedLoginAttempts(0);
        user.setAccountLockUntil(null);
        userRepository.save(user);
    }

    // Deletes a user by userId
    public void deleteUser(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }
}
