package com.nhlstenden.netflixrefactor.services;

import com.nhlstenden.netflixrefactor.models.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);
    Subscription getSubscriptionById(Long id);
    List<Subscription> getAllSubscriptions();
    Subscription updateSubscription(Long id, Subscription subscription);
    void deleteSubscription(Long id);
}
