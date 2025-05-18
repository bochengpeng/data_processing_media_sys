package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.exception.SubscriptionNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.netflix.api.netflix.services.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/netflix/user")
@RequiredArgsConstructor
public class SubscriptionController
{

    private final SubscriptionService subscriptionService;

    // Create a new subscription
    @PostMapping("/{userId}/subscription/create")
    public ResponseEntity<?> createSubscription(
            @PathVariable int userId,
            @Valid @RequestBody SubscriptionDto subscriptionDto,
            BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            SubscriptionDto created = this.subscriptionService.createSubscription(userId, subscriptionDto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (UserNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating subscription: " + e.getMessage());
        }
    }

    // Get subscription by user ID
    @GetMapping("/{userId}/subscription")
    public ResponseEntity<?> getSubscriptionByUserId(@PathVariable int userId)
    {
        try
        {
            SubscriptionDto dto = this.subscriptionService.getSubscriptionByUserId(userId);
            return ResponseEntity.ok(dto);
        } catch (SubscriptionNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found: " + e.getMessage());
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching subscription: " + e.getMessage());
        }
    }

    // Get subscription by ID
    @GetMapping("/{userId}/subscription/{id}")
    public ResponseEntity<?> getSubscriptionById(@PathVariable int userId, @PathVariable int id)
    {
        try
        {
            SubscriptionDto dto = this.subscriptionService.getSubscriptionById(userId, id);
            return ResponseEntity.ok(dto);
        } catch (UserNotFoundException | SubscriptionNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve subscription: " + e.getMessage());
        }
    }

    // Update subscription
    @PutMapping("/{userId}/subscription/{id}/update")
    public ResponseEntity<?> updateSubscription(
            @PathVariable int userId,
            @PathVariable int id,
            @Valid @RequestBody SubscriptionDto subscriptionDto,
            BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            SubscriptionDto updated = this.subscriptionService.updateSubscription(userId, id, subscriptionDto);
            return ResponseEntity.ok(updated);
        } catch (UserNotFoundException | SubscriptionNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update subscription: " + e.getMessage());
        }
    }

    // Delete subscription
    @DeleteMapping("/{userId}/subscription/{id}/delete")
    public ResponseEntity<?> deleteSubscription(@PathVariable int userId, @PathVariable int id)
    {
        try
        {
            this.subscriptionService.deleteSubscription(userId, id);
            return ResponseEntity.ok("Subscription deleted successfully");
        } catch (UserNotFoundException | SubscriptionNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete subscription: " + e.getMessage());
        }
    }
}
