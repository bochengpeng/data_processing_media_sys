package com.nhlstenden.netflixrefactor.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    private int userId;
    private String email;
    private String username;
    private boolean isActivated;
    private LocalDateTime accountLockUntil;
    private int failedLoginAttempt;
}
