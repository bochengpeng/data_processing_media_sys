package com.netflix.api.netflix.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDto
{
    private String username;
    private String password;
    private String email;
    private String inviteCode;
}
