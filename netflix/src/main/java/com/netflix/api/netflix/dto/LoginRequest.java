package com.netflix.api.netflix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest
{

    //    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email format")
    private String email;

    //    @NotBlank(message = "Password is required")
    private String password;


}
