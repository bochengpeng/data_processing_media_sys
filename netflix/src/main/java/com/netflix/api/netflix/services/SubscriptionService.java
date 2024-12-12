package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDto createSubscription(int userId, SubscriptionDto subscriptionDto);
    SubscriptionDto getSubscriptionByUserId(int userId);

    SubscriptionDto getSubscriptionById(int subscriptionId);

//    List<SubscriptionDto> getSubscriptionsByUserId(int userId);

    SubscriptionDto updateSubscription(int userId, SubscriptionDto subscriptionDto);
    void deleteSubscription(int userId);
}

