package com.netflix.api.netflix.dto;

import lombok.Data;

@Data
public class AuthResponseDTO
{
    private String accessToken;
    private String tokenType = "Bearer ";
}
