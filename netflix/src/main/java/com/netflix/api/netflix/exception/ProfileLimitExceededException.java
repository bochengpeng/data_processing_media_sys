package com.netflix.api.netflix.exception;

public class ProfileLimitExceededException extends Exception
{
    public ProfileLimitExceededException(String message)
    {
        super(message);
    }
}
