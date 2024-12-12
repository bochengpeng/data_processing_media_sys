package com.netflix.api.netflix.dto;

import lombok.Data;

@Data
public class LoginDto
{
    private String username;
    private String password;
    private String email;
}
