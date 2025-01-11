package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>
{
//    Subscription findByUserId(int userId);
    @Query("SELECT s FROM Subscription s JOIN s.users u WHERE u.userId = :userId")
    Optional<Subscription> findByUserId(@Param("userId") int userId);
    @Query("SELECT s FROM Subscription s JOIN FETCH s.users WHERE s.id = :id")
    Optional<Subscription> findByIdWithUsers(@Param("id") int id);

    @Query("SELECT COUNT(s) > 0 FROM Subscription s JOIN s.users u WHERE s.subscriptionId = :subscriptionId AND u.userId = :userId")
    boolean existsBySubscriptionIdAndUserId(@Param("subscriptionId") int subscriptionId, @Param("userId") int userId);
}
