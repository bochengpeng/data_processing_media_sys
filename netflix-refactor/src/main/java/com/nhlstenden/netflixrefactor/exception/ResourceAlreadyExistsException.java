package com.nhlstenden.netflixrefactor.exception;

public class ResourceAlreadyExistsException extends Exception
{
    public ResourceAlreadyExistsException(String message)
    {
        super(message);
    }
}
