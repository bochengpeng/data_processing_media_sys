package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.SignUpDto;
import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.ResourceAlreadyExistsException;
import com.netflix.api.netflix.exception.UserNotFoundException;

public interface UserService
{
    UserDto createUser(SignUpDto signUpDto) throws ResourceAlreadyExistsException;

    UserDto getUserById(int userId) throws UserNotFoundException;

    UserDto updateUser(UserDto userDto, int userId) throws UserNotFoundException;

    void deleteUser(int userId) throws UserNotFoundException;

    void activateUser(String token);
    void resetPassword(String token, String newPassword);
    void requestPasswordReset(String email) throws UserNotFoundException;
}

