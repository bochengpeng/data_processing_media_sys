package com.nhlstenden.netflixrefactor.exception;

public class ProfileNotFoundException extends Exception
{
    public ProfileNotFoundException(String message)
    {
        super(message);
    }
}
