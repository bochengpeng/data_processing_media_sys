package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.models.SubscriptionTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>
{
    @Query("SELECT s FROM Subscription s WHERE s.user.userId = :userId")
    Optional<Subscription> findByUserId(@Param("userId") int userId);

    @Query("SELECT s FROM Subscription s JOIN FETCH s.user WHERE s.subscriptionId = :id")
    Optional<Subscription> findByIdWithUser(@Param("id") int id);

    @Query("SELECT COUNT(s) > 0 FROM Subscription s WHERE s.subscriptionId = :subscriptionId AND s.user.userId = :userId")
    boolean existsBySubscriptionIdAndUserId(@Param("subscriptionId") int subscriptionId, @Param("userId") int userId);

    Optional<Subscription> findByTier(SubscriptionTier tier);
}
