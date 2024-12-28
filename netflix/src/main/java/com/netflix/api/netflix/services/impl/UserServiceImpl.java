package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.dto.UserDto;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto)
    {
        User user = mapToEntity(userDto);
        User newUser = userRepository.save(user);
        return mapToDto(newUser);
    }

    @Override
    public UserDto getUserById(int userId) throws UserNotFoundException
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToDto(user);
    }

//    @Override
//    public List<UserDto> getAllUsers()
//    {
//        List<User> users = userRepository.findAll();
//        return users.stream().map(this::mapToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDto updateUser(int userId, UserDto userDto)
//    {
//        return null;
//    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) throws UserNotFoundException
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for update"));

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setActivated(userDto.isActivated());

        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public void deleteUser(int userId) throws UserNotFoundException
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for delete"));
        userRepository.delete(user);
    }

    private UserDto mapToDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setActivated(user.isActivated());
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    private User mapToEntity(UserDto userDto)
    {
        return new User(userDto.getUserId(), userDto.getEmail(), userDto.getPassword(), userDto.isActivated());
    }
}

