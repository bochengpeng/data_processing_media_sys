package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.exception.SubscriptionNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repositories.SubscriptionRepository;
import com.netflix.api.netflix.repositories.UserRepository;
import com.netflix.api.netflix.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService
{

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository)
    {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public SubscriptionDto createSubscription(int userId, SubscriptionDto subscriptionDto) throws UserNotFoundException
    {
        try
        {
            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            if (user.getSubscription() != null)
            {
                throw new IllegalStateException("User already has an active subscription.");
            }

            Subscription subscription = this.subscriptionRepository.findByTier(subscriptionDto.getTier())
                    .orElseGet(() ->
                    {
                        Subscription newSubscription = new Subscription();
                        newSubscription.setTier(subscriptionDto.getTier());
                        newSubscription.setStartDate(LocalDate.now());
                        newSubscription.setNextBillingDate(subscriptionDto.getNextBillingDate());
                        newSubscription.setTrialPeriod(subscriptionDto.isTrialPeriod());
                        return this.subscriptionRepository.save(newSubscription);
                    });

            user.setSubscription(subscription);
            this.userRepository.save(user);
            subscription.setUser(user);
            this.subscriptionRepository.save(subscription);

            return mapToDto(subscription);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create subscription: " + e.getMessage(), e);
        }
    }

    @Override
    public SubscriptionDto getSubscriptionByUserId(int userId) throws SubscriptionNotFoundException
    {
        try
        {
            Subscription subscription = this.subscriptionRepository.findByUserId(userId)
                    .orElseThrow(() -> new SubscriptionNotFoundException("No active subscription found for user with ID: " + userId));
            return mapToDto(subscription);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to fetch subscription by user ID: " + e.getMessage(), e);
        }
    }

    @Override
    public SubscriptionDto getSubscriptionById(int userId, int subscriptionId) throws SubscriptionNotFoundException, UserNotFoundException
    {
        try
        {
            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

            Subscription subscription = this.subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(() -> new SubscriptionNotFoundException("Subscription with ID " + subscriptionId + " not found"));

            if (!subscription.getUser().equals(user))
            {
                throw new SubscriptionNotFoundException("Subscription with ID " + subscriptionId + " is not associated with User ID " + userId);
            }

            return mapToDto(subscription);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to fetch subscription by ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public SubscriptionDto updateSubscription(int userId, int subscriptionId, SubscriptionDto subscriptionDto) throws UserNotFoundException, SubscriptionNotFoundException
    {
        try
        {
            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            Subscription subscription = this.subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));

            if (!subscription.getUser().equals(user))
            {
                throw new SubscriptionNotFoundException("This subscription is not associated with the user.");
            }

            subscription.setTier(subscriptionDto.getTier());
            subscription.setStartDate(subscriptionDto.getStartDate());
            subscription.setTrialPeriod(subscriptionDto.isTrialPeriod());
            subscription.setNextBillingDate(subscriptionDto.getNextBillingDate());

            Subscription updatedSubscription = this.subscriptionRepository.save(subscription);
            return mapToDto(updatedSubscription);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update subscription: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void deleteSubscription(int userId, int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException
    {
        try
        {
            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            Subscription subscription = this.subscriptionRepository.findByIdWithUser(subscriptionId)
                    .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));

            if (!subscription.getUser().equals(user))
            {
                throw new SubscriptionNotFoundException("This subscription is not associated with the user.");
            }

            subscription.setUser(null);
            user.setSubscription(null);

            this.subscriptionRepository.save(subscription);
            this.userRepository.save(user);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to delete subscription: " + e.getMessage(), e);
        }
    }

    // Helper method to map Subscription entity to SubscriptionDto
    private SubscriptionDto mapToDto(Subscription subscription)
    {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setSubscriptionId(subscription.getSubscriptionId());
        subscriptionDto.setStartDate(subscription.getStartDate());
        subscriptionDto.setNextBillingDate(subscription.getNextBillingDate());
        subscriptionDto.setTier(subscription.getTier());
        subscriptionDto.setTrialPeriod(subscription.isTrialPeriod());

        return subscriptionDto;
    }

    // Helper method to map SubscriptionDto to Subscription entity
    private Subscription mapToEntity(SubscriptionDto subscriptionDto)
    {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(subscriptionDto.getSubscriptionId());
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setNextBillingDate(subscriptionDto.getNextBillingDate());
        subscription.setTier(subscriptionDto.getTier());
        subscription.setTrialPeriod(subscriptionDto.isTrialPeriod());

        return subscription;
    }
}
