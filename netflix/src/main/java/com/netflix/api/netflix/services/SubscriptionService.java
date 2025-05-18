package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.exception.SubscriptionNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;

public interface SubscriptionService
{
    SubscriptionDto createSubscription(int userId, SubscriptionDto subscriptionDto) throws UserNotFoundException;
    SubscriptionDto getSubscriptionByUserId(int userId) throws SubscriptionNotFoundException;
    SubscriptionDto getSubscriptionById(int subscriptionId, int id) throws SubscriptionNotFoundException, UserNotFoundException;
    SubscriptionDto updateSubscription(int userId, int subscriptionId, SubscriptionDto subscriptionDto) throws UserNotFoundException, SubscriptionNotFoundException;
    void deleteSubscription(int userId, int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException;
}

