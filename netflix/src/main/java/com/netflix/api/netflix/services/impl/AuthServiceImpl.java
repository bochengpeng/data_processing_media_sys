package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.AuthService;
import com.netflix.api.netflix.services.EmailService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService
{
    private final UserRepository userRepository;
    private final EmailService emailService;

    public AuthServiceImpl(UserRepository userRepository, EmailService emailService)
    {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public void authenticate(String email, String password)
    {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        this.assertAccountActiveness(user);
        this.authenticateUserPassword(user, password);

        user.setFailedLoginAttempts(0); // Reset on successful login
        this.userRepository.save(user);
    }

    private void authenticateUserPassword(User user, String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(password, user.getPassword()))
        {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);

            if (user.getFailedLoginAttempts() >= 3)
            {
                user.setAccountLockUntil(LocalDateTime.now().plusMinutes(15)); // Lock for 15 minutes
                user.setFailedLoginAttempts(0); // Reset attempts
            }

            this.userRepository.save(user);
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    private void assertAccountActiveness(User user)
    {
        if (user.getAccountLockUntil() != null && user.getAccountLockUntil().isAfter(LocalDateTime.now()))
        {
            throw new IllegalArgumentException("Account is temporarily locked. Try again later.");
        }
    }

    @Override
    public void initiatePasswordReset(String email)
    {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));

        user.setResetToken(UUID.randomUUID().toString());
//        user.setTokenExpiryTime(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
        this.userRepository.save(user);

        this.emailService.sendPasswordResetEmail(user.getEmail(), user.getResetToken());
    }

    @Override
    public void resetPassword(String token, String newPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = this.userRepository.findByResetToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired token"));

//        if (user.getTokenExpiryTime().isBefore(LocalDateTime.now())) {
//            throw new IllegalArgumentException("Token has expired");
//        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null); // Clear the token
//        user.setTokenExpiryTime(null);
        this.userRepository.save(user);
    }
}
