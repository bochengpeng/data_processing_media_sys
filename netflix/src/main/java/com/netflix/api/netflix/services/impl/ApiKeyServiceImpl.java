package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.ApiKey;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repositories.ApiKeyRepository;
import com.netflix.api.netflix.repositories.UserRepository;
import com.netflix.api.netflix.services.ApiKeyService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ApiKeyServiceImpl implements ApiKeyService
{
    private final UserRepository userRepository;
    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyServiceImpl(UserRepository userRepository, ApiKeyRepository apiKeyRepository)
    {
        this.userRepository = userRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public String generateApiKey()
    {
        return UUID.randomUUID().toString();
    }

    @Transactional
    @Override
    public void saveApiKey(String apiKey, int userId) throws UserNotFoundException
    {
        try
        {
            if (apiKey == null || apiKey.trim().isEmpty())
            {
                throw new IllegalArgumentException("API key cannot be null or empty");
            }

            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            Hibernate.initialize(user.getProfiles());

            ApiKey apiKeyEntity = new ApiKey();
            apiKeyEntity.setKey(apiKey);
            apiKeyEntity.setUser(user);
            apiKeyEntity.setCreatedAt(LocalDateTime.now());
            apiKeyEntity.setExpiresAt(LocalDateTime.now().plusDays(30));

            this.apiKeyRepository.save(apiKeyEntity);
        } catch (UserNotFoundException e)
        {
            // Rethrow known exceptions so they can be handled upstream
            throw e;
        } catch (IllegalArgumentException e)
        {
            // Optionally wrap or rethrow
            throw new RuntimeException("Invalid input: " + e.getMessage());
        } catch (Exception e)
        {
            // Log the actual error in production systems
            throw new RuntimeException("Failed to save API key: " + e.getMessage());
        }
    }
}
