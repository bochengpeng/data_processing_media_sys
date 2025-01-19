package com.netflix.api.netflix.services;

import com.netflix.api.netflix.exception.UserNotFoundException;

public interface ApiKeyService
{
    String generateApiKey();
    void saveApiKey(String apiKey, int userId) throws UserNotFoundException;
    boolean validateApiKey(String apiKey);
}
