package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.exception.SubscriptionNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import com.netflix.api.netflix.services.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix")
public class SubscriptionController
{
    private SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService)
    {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/user/{userId}/subscription")
    public ResponseEntity<SubscriptionDto> createSubscription(@PathVariable(value = "userId") int userId, @RequestBody SubscriptionDto subscriptionDto) throws UserNotFoundException
    {
        return new ResponseEntity<>(subscriptionService.createSubscription(userId, subscriptionDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/subscription")
    public SubscriptionDto getSubscriptionByUserId(@PathVariable(value = "userId") int userId) {
        return subscriptionService.getSubscriptionByUserId(userId);
    }

    @GetMapping("/user/{userId}/subscription/{id}")
    public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable(value = "userId") int userId, @PathVariable(value = "id") int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException
    {
        SubscriptionDto subscriptionDto = subscriptionService.getSubscriptionById(userId, subscriptionId);
        return new ResponseEntity<>(subscriptionDto, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/subscription/{id}")
    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable(value = "userId") int userId, @PathVariable(value = "id") int subscriptionId,
                                                              @RequestBody SubscriptionDto subscriptionDto) throws UserNotFoundException, SubscriptionNotFoundException
    {
        SubscriptionDto updatedSubscription = subscriptionService.updateSubscription(userId, subscriptionId, subscriptionDto);
        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/subscription/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable(value = "userId") int userId, @PathVariable(value = "id") int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException
    {
        subscriptionService.deleteSubscription(userId, subscriptionId);
        return new ResponseEntity<>("Subscription deleted successfully", HttpStatus.OK);
    }
}
