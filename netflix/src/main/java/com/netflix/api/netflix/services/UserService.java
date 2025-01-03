package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.LoginDto;
import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.ResourceAlreadyExistsException;
import com.netflix.api.netflix.exception.UserNotFoundException;

import java.util.List;

public interface UserService
{
    UserDto createUser(LoginDto loginDto) throws ResourceAlreadyExistsException;

    UserDto getUserById(int userId) throws UserNotFoundException;

    UserDto updateUser(UserDto userDto, int userId) throws UserNotFoundException;

    void deleteUser(int userId) throws UserNotFoundException;

    void activateUser(String token);
}

