package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.dto.SubscriptionResponse;
import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.SubscriptionRepository;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService
{

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

//    @Override
//    public SubscriptionDto createSubscription(int userId, SubscriptionDto subscriptionDto) {
//        // Fetch the user by ID to associate the subscription with the user
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Convert SubscriptionDto to Subscription entity
//        Subscription subscription = mapToEntity(subscriptionDto);
////        subscription.setUser(user); // Associate the subscription with the user
//
//        // Save the subscription entity
//        Subscription savedSubscription = subscriptionRepository.save(subscription);
//
//        // Convert the saved subscription entity to a SubscriptionDto and return it
//        return mapToDto(savedSubscription);
//    }

    @Override
    public SubscriptionDto createSubscription(Subscription subscription) {
        // Save the subscription to the database
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        // Manually map Subscription to SubscriptionDto
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setSubscriptionId(savedSubscription.getSubscriptionId());
        subscriptionDto.setTier(savedSubscription.getTier());
        subscriptionDto.setStartDate(savedSubscription.getStartDate());  // Assuming it's a LocalDate or similar
        subscriptionDto.setNextBillingDate(savedSubscription.getNextBillingDate());  // Assuming it's a LocalDate or similar

        // Return the DTO
        return subscriptionDto;
    }

    @Override
    public SubscriptionDto getSubscriptionByUserId(int userId)
    {
        return null;
    }

    @Override
    public SubscriptionDto getSubscriptionById(int subscriptionId) {
        // Retrieve the subscription by its ID
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        // Convert Subscription entity to SubscriptionDto and return it
        return mapToDto(subscription);
    }

//    @Override
//    public List<SubscriptionDto> getSubscriptionsByUserId(int userId) {
//        // Retrieve all subscriptions associated with the user
//        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
//
//        // Convert Subscription entities to SubscriptionDto and return the list
//        return subscriptions.stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public SubscriptionDto updateSubscription(int subscriptionId, SubscriptionDto subscriptionDto) {
        // Retrieve the existing subscription by ID
        Subscription existingSubscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        // Update the subscription fields with the data from the DTO
        existingSubscription.setStartDate(subscriptionDto.getStartDate());
        existingSubscription.setNextBillingDate(subscriptionDto.getNextBillingDate());
        existingSubscription.setTier(subscriptionDto.getTier());

        // Save the updated subscription entity
        Subscription updatedSubscription = subscriptionRepository.save(existingSubscription);

        // Convert the updated subscription entity to SubscriptionDto and return it
        return mapToDto(updatedSubscription);
    }

    @Override
    public void deleteSubscription(int subscriptionId) {
        // Retrieve the subscription by ID
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        // Delete the subscription entity
        subscriptionRepository.delete(subscription);
    }
    

    // Helper method to map Subscription entity to SubscriptionDto
    private SubscriptionDto mapToDto(Subscription subscription) {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setSubscriptionId(subscription.getSubscriptionId());
        subscriptionDto.setStartDate(subscription.getStartDate());
        subscriptionDto.setNextBillingDate(subscription.getNextBillingDate());
        subscriptionDto.setTier(subscription.getTier());
        return subscriptionDto;
    }

    // Helper method to map SubscriptionDto to Subscription entity
    private Subscription mapToEntity(SubscriptionDto subscriptionDto) {
        Subscription subscription = new Subscription();
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setNextBillingDate(subscriptionDto.getNextBillingDate());
        subscription.setTier(subscriptionDto.getTier());
        return subscription;
    }
}
