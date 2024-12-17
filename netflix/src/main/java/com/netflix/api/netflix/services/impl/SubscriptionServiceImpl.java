package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.exception.SubscriptionNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.SubscriptionRepository;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    @Override
    public SubscriptionDto createSubscription(int userId, SubscriptionDto subscriptionDto) throws UserNotFoundException
    {
        Subscription subscription = mapToEntity(subscriptionDto);
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("User with associated subscription not found"));
        subscription.setUserId(user.getUserId());
        Subscription newSubscription = subscriptionRepository.save(subscription);
        return mapToDto(newSubscription);
    }

    @Override
    public SubscriptionDto getSubscriptionByUserId(int userId)
    {
        Subscription subscription = subscriptionRepository.findByUserId(userId);

        return mapToDto(subscription);
    }

    @Override
    public SubscriptionDto getSubscriptionById(int userId, int subscriptionId) throws SubscriptionNotFoundException, UserNotFoundException
    {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated subscription not found"));

        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription with associate user not found"));

        if(subscription.getUserId() != user.getUserId()) {
            throw new SubscriptionNotFoundException("This review does not belong to a pokemon");
        }

        return mapToDto(subscription);
    }

    @Override
    public SubscriptionDto updateSubscription(int userId, int subscriptionId, SubscriptionDto subscriptionDto) throws UserNotFoundException, SubscriptionNotFoundException
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated subscription not found"));
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription with associated user not found"));
        if (subscription.getUserId() != user.getUserId())
        {
            throw new SubscriptionNotFoundException("This subscription does not belong to a user");
        }

        subscription.setTier(subscriptionDto.getTier());
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setTrialPeriod(subscriptionDto.isTrialPeriod());
        subscription.setNextBillingDate(subscriptionDto.getNextBillingDate());

        Subscription updateSubscription = subscriptionRepository.save(subscription);
        return mapToDto(updateSubscription);
    }

    @Override
    public void deleteSubscription(int userId, int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated subscription not found"));
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription with associated user not found"));

        if (subscription.getUserId() != user.getUserId())
        {
            throw new SubscriptionNotFoundException("This subscription does not belong to a user");
        }

        subscriptionRepository.delete(subscription);
    }

    // Helper method to map Subscription entity to SubscriptionDto
    private SubscriptionDto mapToDto(Subscription subscription)
    {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setSubscriptionId(subscription.getSubscriptionId());
        subscriptionDto.setStartDate(subscription.getStartDate());
        subscriptionDto.setNextBillingDate(subscription.getNextBillingDate());
        subscriptionDto.setTier(subscription.getTier());
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
        return subscription;
    }
}
