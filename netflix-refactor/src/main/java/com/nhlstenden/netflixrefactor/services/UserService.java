package com.nhlstenden.netflixrefactor.services;

import com.nhlstenden.netflixrefactor.models.User;

public interface UserService {
    User registerUser(User user);
    void activateUser(String token);
    User getUserByEmail(String email);
    void incrementFailedLogin(String email);
    void resetPassword(String token, String newPassword);
    void requestPasswordReset(String email);
}

