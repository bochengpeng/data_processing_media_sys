package com.nhlstenden.netflixrefactor.exception;

public class UserNotFoundException extends Exception
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
