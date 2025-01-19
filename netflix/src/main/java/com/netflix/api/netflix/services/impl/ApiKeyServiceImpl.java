package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.ApiKey;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.ApiKeyRepository;
import com.netflix.api.netflix.repository.UserRepository;
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
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Ensure profiles collection is initialized if needed
        Hibernate.initialize(user.getProfiles());

        ApiKey apiKeyEntity = new ApiKey();
        apiKeyEntity.setKey(apiKey);
        apiKeyEntity.setUser(user);
        apiKeyEntity.setCreatedAt(LocalDateTime.now());
        apiKeyEntity.setExpiresAt(LocalDateTime.now().plusDays(30));

        this.apiKeyRepository.save(apiKeyEntity);
    }

    @Override
    public boolean validateApiKey(String apiKey)
    {
        return this.apiKeyRepository.findByKey(apiKey)
                .map(apiKeyEntity -> !apiKeyEntity.getExpiresAt().isBefore(LocalDateTime.now()))
                .orElse(false);
    }
}
