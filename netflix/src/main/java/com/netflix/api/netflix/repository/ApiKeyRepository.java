package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Integer>
{
    Optional<ApiKey> findByKey(String apiKey);
}
