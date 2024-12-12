package org.example.dataprocessing.repository;

import org.example.dataprocessing.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>
{
    List<Subscription> findBySubscriptionId(Long id);
}

