package com.nhlstenden.netflixrefactor.services.serviceimpl;

import com.nhlstenden.netflixrefactor.models.User;
import com.nhlstenden.netflixrefactor.repositories.UserRepository;
import com.nhlstenden.netflixrefactor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        user.setActivationToken(UUID.randomUUID().toString());
        user.setActivationTokenExpiry(LocalDateTime.now().plusDays(1));
        user.setActivated(false);
        user.setFailedLoginAttempts(0);
        return userRepository.save(user);
    }


    @Override
    public void activateUser(String token) {
        User user = userRepository.findByActivationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid activation token"));
        if (user.getActivationTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Activation token expired");
        }
        user.setActivated(true);
        user.setActivationToken(null);
        user.setActivationTokenExpiry(null);
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void incrementFailedLogin(String email) {
        User user = getUserByEmail(email);
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        if (user.getFailedLoginAttempts() >= 3) {
            user.setAccountLockedUntil(LocalDateTime.now().plusMinutes(30));
        }
        userRepository.save(user);
    }

    @Override
    public void requestPasswordReset(String email) {
        User user = getUserByEmail(email);
        user.setResetToken(UUID.randomUUID().toString());
        user.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
    }


    @Override
    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid reset token"));
        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Reset token expired");
        }
        user.setPassword(newPassword);
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);
    }
}
