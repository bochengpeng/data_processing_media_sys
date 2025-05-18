package com.netflix.api.netflix.services;

public interface AuthService
{
    void authenticate(String email, String password);
}
