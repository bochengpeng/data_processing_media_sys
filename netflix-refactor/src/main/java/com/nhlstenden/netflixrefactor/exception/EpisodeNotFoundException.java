package com.nhlstenden.netflixrefactor.exception;

public class EpisodeNotFoundException extends Exception
{
    public EpisodeNotFoundException(String message)
    {
        super(message);
    }
}
