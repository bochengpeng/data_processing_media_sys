package com.netflix.api.netflix.dto;

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
public class UserDto {
    private int userId;
    private String email;
    private String password;
    private boolean isActivated;
    private LocalDateTime createdAt;
    private List<ProfileDto> profiles;
    private SubscriptionDto subscription;

    public UserDto(int userId, String email, String password, boolean activated)
    {
    }
}
