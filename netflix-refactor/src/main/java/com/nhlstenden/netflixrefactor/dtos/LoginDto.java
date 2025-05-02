package com.nhlstenden.netflixrefactor.dtos;

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
