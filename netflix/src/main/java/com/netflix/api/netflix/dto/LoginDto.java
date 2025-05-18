package com.netflix.api.netflix.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto
{
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email format is invalid")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 1, max = 100, message = "Password cannot be empty and must be less than 100 characters")
    private String password;
}
