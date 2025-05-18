package com.netflix.api.netflix.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDto
{
    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username must be less than 100 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 100, message = "Password must be less than 100 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @Size(max = 100, message = "Invite code must be less than 100 characters")
    private String inviteCode;
}
