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

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable int id) {
        return subscriptionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable int id, @RequestBody Subscription subscriptionDetails) {
        return subscriptionRepository.findById(id)
                .map(subscription -> {
//                    subscription.setPlan(subscriptionDetails.getPlan());
                    return ResponseEntity.ok(subscriptionRepository.save(subscription));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable int id) {
        return subscriptionRepository.findById(id)
                .map(subscription -> {
                    subscriptionRepository.delete(subscription);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}