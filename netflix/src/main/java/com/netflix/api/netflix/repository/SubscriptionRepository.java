package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>
{
    Subscription findByUserId(int userId);
}
