package com.nhlstenden.netflixrefactor.exception;

public class TMDBApiException extends Exception
{
    public TMDBApiException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
