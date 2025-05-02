package com.nhlstenden.netflixrefactor.exception;

public class SubscriptionNotFoundException extends Exception
{
    public SubscriptionNotFoundException(String message)
    {
        super(message);
    }
}
