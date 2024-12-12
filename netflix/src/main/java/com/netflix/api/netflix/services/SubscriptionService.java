package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.models.Subscription;

public interface SubscriptionService {
    SubscriptionDto createSubscription(Subscription subscription);
    SubscriptionDto getSubscriptionByUserId(int userId);

    SubscriptionDto getSubscriptionById(int subscriptionId);

//    List<SubscriptionDto> getSubscriptionsByUserId(int userId);

    SubscriptionDto updateSubscription(int userId, SubscriptionDto subscriptionDto);
    void deleteSubscription(int userId);
}

