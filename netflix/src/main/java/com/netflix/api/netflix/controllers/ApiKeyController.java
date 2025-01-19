package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.services.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/netflix/key")
public class ApiKeyController
{
    private final ApiKeyService apiKeyService;

    @Autowired
    public ApiKeyController(ApiKeyService apiKeyService)
    {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("/generate-key")
    public String generateApiKey(@RequestParam int userId) throws UserNotFoundException
    {
        String apiKey = this.apiKeyService.generateApiKey();
        this.apiKeyService.saveApiKey(apiKey, userId);
        return apiKey;
    }
}

