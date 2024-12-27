package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.dto.SubscriptionResponse;
import com.netflix.api.netflix.exception.SubscriptionNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.Subscription;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDto createSubscription(int userId, SubscriptionDto subscriptionDto) throws UserNotFoundException;
    SubscriptionDto getSubscriptionByUserId(int userId) throws SubscriptionNotFoundException;

    SubscriptionDto getSubscriptionById(int subscriptionId, int id) throws SubscriptionNotFoundException, UserNotFoundException;

    SubscriptionDto updateSubscription(int userId, int subscriptionId, SubscriptionDto subscriptionDto) throws UserNotFoundException, SubscriptionNotFoundException;
    void deleteSubscription(int userId, int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException;

    SubscriptionDto getSubscriptionByUserIdAndSubscriptionId(int userId, int subscriptionId) throws SubscriptionNotFoundException, UserNotFoundException;
}

