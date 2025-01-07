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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    @Override
//    public SubscriptionDto createSubscription(int userId, SubscriptionDto subscriptionDto) throws UserNotFoundException
//    {
//        Subscription subscription = mapToEntity(subscriptionDto);
//        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("User with associated subscription not found"));
//        subscription.setUserId(user.getUserId());
//        Subscription newSubscription = subscriptionRepository.save(subscription);
//        return mapToDto(newSubscription);
//    }

    @Override
    public SubscriptionDto createSubscription(int userId, SubscriptionDto subscriptionDto) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getSubscription() != null) {
            throw new IllegalStateException("User already has an active subscription.");
        }

        Subscription subscription = mapToEntity(subscriptionDto);
        subscription.getUsers().add(user);
        user.setSubscription(subscription);

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapToDto(savedSubscription);
    }

//    @Override
//    public SubscriptionDto getSubscriptionByUserId(int userId)
//    {
//        Subscription subscription = subscriptionRepository.findByUserId(userId);
//
//        return mapToDto(subscription);
//    }

    @Override
    public SubscriptionDto getSubscriptionByUserId(int userId) throws SubscriptionNotFoundException
    {
        // Use the repository method, which returns Optional<Subscription>
        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new SubscriptionNotFoundException("No active subscription found for user with ID: " + userId));

        // Convert the Subscription entity to a DTO and return
        return mapToDto(subscription);
    }


//    @Override
//    public SubscriptionDto getSubscriptionById(int userId, int subscriptionId) throws SubscriptionNotFoundException, UserNotFoundException
//    {
//
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated subscription not found"));
//
//        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription with associate user not found"));
//
//        if(subscription.getUserId() != user.getUserId()) {
//            throw new SubscriptionNotFoundException("This review does not belong to a pokemon");
//        }
//
//        return mapToDto(subscription);
//    }

    public SubscriptionDto getSubscriptionById(int userId, int subscriptionId) throws SubscriptionNotFoundException, UserNotFoundException {
        // Fetch the user
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        // Fetch the subscription
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription with ID " + subscriptionId + " not found"));

        // Check if the subscription is associated with the user
        if (!subscription.getUsers().contains(user)) {
            throw new SubscriptionNotFoundException("Subscription with ID " + subscriptionId + " is not associated with User ID " + userId);
        }

        // Map subscription entity to DTO and return
        return mapToDto(subscription);
    }


//    @Override
//    public SubscriptionDto updateSubscription(int userId, int subscriptionId, SubscriptionDto subscriptionDto) throws UserNotFoundException, SubscriptionNotFoundException
//    {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated subscription not found"));
//        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription with associated user not found"));
//        if (subscription.getUserId() != user.getUserId())
//        {
//            throw new SubscriptionNotFoundException("This subscription does not belong to a user");
//        }
//
//        subscription.setTier(subscriptionDto.getTier());
//        subscription.setStartDate(subscriptionDto.getStartDate());
//        subscription.setTrialPeriod(subscriptionDto.isTrialPeriod());
//        subscription.setNextBillingDate(subscriptionDto.getNextBillingDate());
//
//        Subscription updateSubscription = subscriptionRepository.save(subscription);
//        return mapToDto(updateSubscription);
//    }

    @Override
    public SubscriptionDto updateSubscription(int userId, int subscriptionId, SubscriptionDto subscriptionDto) throws UserNotFoundException, SubscriptionNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));

        if (!subscription.getUsers().contains(user)) {
            throw new SubscriptionNotFoundException("This subscription is not associated with the user.");
        }

        subscription.setTier(subscriptionDto.getTier());
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setTrialPeriod(subscriptionDto.isTrialPeriod());
        subscription.setNextBillingDate(subscriptionDto.getNextBillingDate());

        Subscription updatedSubscription = subscriptionRepository.save(subscription);
        return mapToDto(updatedSubscription);
    }


//    @Override
//    public void deleteSubscription(int userId, int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException
//    {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated subscription not found"));
//        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription with associated user not found"));
//
//        if (subscription.getUserId() != user.getUserId())
//        {
//            throw new SubscriptionNotFoundException("This subscription does not belong to a user");
//        }
//
//        subscriptionRepository.delete(subscription);
//    }

//    @Override
//    public void deleteSubscription(int userId, int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
//
//        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
//
//        if (!subscription.getUsers().contains(user)) {
//            throw new SubscriptionNotFoundException("This subscription is not associated with the user.");
//        }
//
//        subscription.getUsers().remove(user);
//        user.setSubscription(null);
//
//        subscriptionRepository.save(subscription);
//    }

    @Override
    @Transactional
    public void deleteSubscription(int userId, int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException {
        // Fetch the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Fetch the subscription with users
        Subscription subscription = subscriptionRepository.findByIdWithUsers(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));

        // Check association
        if (!subscription.getUsers().contains(user)) {
            throw new SubscriptionNotFoundException("This subscription is not associated with the user.");
        }

        // Manage bidirectional relationship
        subscription.getUsers().remove(user);
        user.setSubscription(null);

        // Save changes (if necessary)
        subscriptionRepository.save(subscription); // Ensure this is required based on your setup.
        userRepository.save(user); // Explicitly save user if needed.
    }


    @Override
    public SubscriptionDto getSubscriptionByUserIdAndSubscriptionId(int userId, int subscriptionId) throws SubscriptionNotFoundException, UserNotFoundException {
        // Check if the user exists
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        // Fetch the subscription and validate it belongs to the given user
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));

        if (subscription.getUsers().stream().noneMatch(u -> u.getUserId() == userId)) {
            throw new SubscriptionNotFoundException("Subscription does not belong to this user");
        }

        // Map the subscription entity to a DTO and return
        return mapToDto(subscription);
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
