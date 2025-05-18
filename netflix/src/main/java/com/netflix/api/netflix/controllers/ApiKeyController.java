package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.services.ApiKeyService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/netflix/key")
@RequiredArgsConstructor
@Slf4j
public class ApiKeyController
{
    private final ApiKeyService apiKeyService;

    @PostMapping("/generate-key")
    public ResponseEntity<String> generateApiKey(@RequestParam int userId)
    {
        try
        {
            if (userId <= 0)
            {
                throw new ValidationException("User ID must be a positive integer.");
            }

            String apiKey = this.apiKeyService.generateApiKey();
            this.apiKeyService.saveApiKey(apiKey, userId);

            return ResponseEntity.ok(apiKey);
        } catch (UserNotFoundException e)
        {
            log.warn("User not found while generating API key: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Unexpected error generating API key for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate API key.");
        }
    }
}