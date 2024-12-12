package org.example.dataprocessing.controller;

import org.example.dataprocessing.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/invite")
    public ResponseEntity<String> inviteUser(@RequestParam String inviterEmail, @RequestParam String inviteeEmail) {
        subscriptionService.inviteUser(inviterEmail, inviteeEmail);
        return ResponseEntity.ok("Invitation sent successfully.");
    }
}
