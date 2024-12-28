package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(int userId) throws UserNotFoundException;
//    List<UserDto> getAllUsers();
//    UserDto updateUser(int userId, UserDto userDto);

    UserDto updateUser(UserDto userDto, int userId) throws UserNotFoundException;

    void deleteUser(int userId) throws UserNotFoundException;
}

