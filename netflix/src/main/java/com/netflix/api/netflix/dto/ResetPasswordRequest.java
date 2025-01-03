package com.netflix.api.netflix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest 
{

//    @NotBlank(message = "Reset token is required")
    private String token;

//    @NotBlank(message = "New password is required")
//    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String newPassword;

    // Optional: Confirm password for validation on the client side
//    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
}
