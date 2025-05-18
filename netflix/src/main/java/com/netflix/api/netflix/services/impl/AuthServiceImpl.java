package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.exception.AuthenticationFailedException;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repositories.UserRepository;
import com.netflix.api.netflix.services.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AuthServiceImpl implements AuthService
{
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void authenticate(String email, String password)
    {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find email"));

        this.assertAccountActiveness(user);
        this.authenticateUserPassword(user, password);

        user.setFailedLoginAttempts(0); // Reset on successful login
        this.userRepository.save(user);
    }

    @Transactional
    protected void authenticateUserPassword(User user, String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!user.isActivated())
        {
            throw new AuthenticationFailedException("Account is not activated. Please check your email to activate your account.");
        }

        // First check if account is locked
        if (user.getAccountLockUntil() != null && LocalDateTime.now().isBefore(user.getAccountLockUntil()))
        {
            long minutesRemaining = ChronoUnit.MINUTES.between(
                    LocalDateTime.now(), user.getAccountLockUntil()) + 1;
            throw new AuthenticationFailedException("Account is locked. Try again in " +
                    minutesRemaining + " minutes");
        }

        if (!passwordEncoder.matches(password, user.getPassword()))
        {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);

            if (user.getFailedLoginAttempts() >= 3)
            {
                user.setAccountLockUntil(LocalDateTime.now().plusMinutes(15)); // Lock for 15 minutes
                user.setFailedLoginAttempts(0); // Reset attempts

                throw new AuthenticationFailedException("Account locked for 15 minutes due to multiple failed attempts");
            }

            throw new AuthenticationFailedException("Invalid email or ");
        }

        if (user.getFailedLoginAttempts() > 0)
        {
            user.setFailedLoginAttempts(0);
            this.userRepository.save(user);
        }
    }

    private void assertAccountActiveness(User user)
    {
        if (user.getAccountLockUntil() != null && user.getAccountLockUntil().isAfter(LocalDateTime.now()))
        {
            throw new IllegalArgumentException("Account is temporarily locked. Try again later.");
        }
    }
}
