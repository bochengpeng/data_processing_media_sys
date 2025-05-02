package com.nhlstenden.netflixrefactor.exception;

public class ProfileLimitExceededException extends Exception
{
    public ProfileLimitExceededException(String message)
    {
        super(message);
    }
}
