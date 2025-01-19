package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.exception.SubscriptionNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import com.netflix.api.netflix.services.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/netflix/user")
public class SubscriptionController
{
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService)
    {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/{userId}/subscription/create")
    public ResponseEntity<SubscriptionDto> createSubscription(@PathVariable(value = "userId") int userId, @RequestBody SubscriptionDto subscriptionDto) throws UserNotFoundException
    {
        return new ResponseEntity<>(this.subscriptionService.createSubscription(userId, subscriptionDto), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/subscription")
    public ResponseEntity<SubscriptionDto> getSubscriptionByUserId(@PathVariable(value = "userId") int userId) throws SubscriptionNotFoundException
    {
        SubscriptionDto subscriptionDto = this.subscriptionService.getSubscriptionByUserId(userId);
        return new ResponseEntity<>(subscriptionDto, HttpStatus.OK);
    }

    @GetMapping("/{userId}/subscription/{id}")
    public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable(value = "userId") int userId, @PathVariable(value = "id") int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException
    {
        SubscriptionDto subscriptionDto = this.subscriptionService.getSubscriptionById(userId, subscriptionId);
        return new ResponseEntity<>(subscriptionDto, HttpStatus.OK);
    }

    @PutMapping("/{userId}/subscription/{id}/update")
    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable(value = "userId") int userId, @PathVariable(value = "id") int subscriptionId,
                                                              @RequestBody SubscriptionDto subscriptionDto) throws UserNotFoundException, SubscriptionNotFoundException
    {
        SubscriptionDto updatedSubscription = this.subscriptionService.updateSubscription(userId, subscriptionId, subscriptionDto);
        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/subscription/{id}/delete")
    public ResponseEntity<String> deleteSubscription(@PathVariable(value = "userId") int userId, @PathVariable(value = "id") int subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException
    {
        this.subscriptionService.deleteSubscription(userId, subscriptionId);
        return new ResponseEntity<>("Subscription deleted successfully", HttpStatus.OK);
    }
}
