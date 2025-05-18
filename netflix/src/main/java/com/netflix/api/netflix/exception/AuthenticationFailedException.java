package com.netflix.api.netflix.exception;

public class AuthenticationFailedException extends RuntimeException
{
    public AuthenticationFailedException(String message)
    {
        super(message);
    }
}
