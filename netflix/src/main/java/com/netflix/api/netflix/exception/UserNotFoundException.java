package com.netflix.api.netflix.exception;

public class UserNotFoundException extends Exception
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
