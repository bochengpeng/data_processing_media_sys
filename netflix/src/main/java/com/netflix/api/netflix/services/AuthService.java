package com.netflix.api.netflix.services;

public interface AuthService
{
    void authenticate(String email, String password);
    void initiatePasswordReset(String email);
    void resetPassword(String token, String newPassword);
}
