package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable int subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users/{userId}")
    public Subscription createSubscription(@RequestBody Subscription subscription, @PathVariable int userId) {
        return subscriptionRepository.save(subscription);
    }

    @PutMapping("/{subscriptionId}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable int subscriptionId, @RequestBody Subscription subscriptionDetails) {
        return subscriptionRepository.findById(subscriptionId)
                .map(subscription -> {
//                    subscription.setPlan(subscriptionDetails.getPlan());
                    return ResponseEntity.ok(subscriptionRepository.save(subscription));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable int subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .map(subscription -> {
                    subscriptionRepository.delete(subscription);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}