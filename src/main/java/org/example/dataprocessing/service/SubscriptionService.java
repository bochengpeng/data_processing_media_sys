package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.Subscription;
import org.example.dataprocessing.entity.SubscriptionTier;
import org.example.dataprocessing.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    // Constructor Injection
    public SubscriptionService(SubscriptionRepository subscriptionRepository)
    {
        this.subscriptionRepository = subscriptionRepository;
    }

    // Create a new subscription
    public Subscription createSubscription(Subscription subscription)
    {
        return subscriptionRepository.save(subscription);
    }

    // Get a subscription by ID
    public Optional<Subscription> getSubscriptionById(Long id)
    {
        return subscriptionRepository.findById(id);
    }

    // Fetch all subscriptions
    public List<Subscription> getAllSubscriptions()
    {
        return subscriptionRepository.findAll();
    }

    // Update a subscription
    public Subscription updateSubscription(Long id, Subscription updatedSubscription)
    {
        return subscriptionRepository.findById(id).map(existingSubscription ->
        {
            existingSubscription.setTier(updatedSubscription.getTier());
            return subscriptionRepository.save(existingSubscription);
        }).orElseThrow(() -> new IllegalArgumentException("Subscription not found with ID: " + id));
    }

    // Delete a subscription by ID
    public void deleteSubscription(Long id)
    {
        if (!subscriptionRepository.existsById(id))
        {
            throw new IllegalArgumentException("Subscription not found with ID: " + id);
        }
        subscriptionRepository.deleteById(id);
    }

    // Fetch subscriptions by tier type
    public List<Subscription> getSubscriptionsByTier(SubscriptionTier tier)
    {
        return subscriptionRepository.findAll()
                .stream()
                .filter(subscription -> subscription.getTier() == tier)
                .toList();
    }
}
