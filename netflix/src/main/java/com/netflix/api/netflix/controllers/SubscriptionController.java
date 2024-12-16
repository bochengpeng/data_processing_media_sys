package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.SubscriptionDto;
import org.springframework.ui.Model;
import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.repository.SubscriptionRepository;
import com.netflix.api.netflix.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/netflix/subscriptions")
public class SubscriptionController
{
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    private SubscriptionService subscriptionService;



    @GetMapping("/subscriptions")
    public List<Subscription> getAllSubscriptions()
    {
        return subscriptionRepository.findAll();
    }

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable int subscriptionId)
    {
        return subscriptionRepository.findById(subscriptionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users/{userId}")
    public Subscription createSubscription(@RequestBody Subscription subscription, @PathVariable int userId)
    {
        return subscriptionRepository.save(subscription);
    }

    @PutMapping("/{subscriptionId}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable int subscriptionId, @RequestBody Subscription subscriptionDetails)
    {
        return subscriptionRepository.findById(subscriptionId)
                .map(subscription ->
                {
//                    subscription.setPlan(subscriptionDetails.getPlan());
                    return ResponseEntity.ok(subscriptionRepository.save(subscription));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable int subscriptionId)
    {
        return subscriptionRepository.findById(subscriptionId)
                .map(subscription ->
                {
                    subscriptionRepository.delete(subscription);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


//
//package com.netflix.api.netflix.controllers;
//
//import com.netflix.api.netflix.dto.SubscriptionDto;
//import com.netflix.api.netflix.models.Subscription;
//import com.netflix.api.netflix.repository.SubscriptionRepository;
//import com.netflix.api.netflix.services.SubscriptionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/netflix/subscriptions")
//public class SubscriptionController {
//
//    @Autowired
//    private SubscriptionService subscriptionService;
//
//    @Autowired
//    private SubscriptionRepository subscriptionRepository;
//
////    // Serve subscriptions for the HTML view (Thymeleaf or Frontend)
////    @GetMapping("/subscriptions")
////    public String viewSubscriptions(Model model) {
////        model.addAttribute("subscriptions", subscriptionService.getAllSubscriptions());
////        return "subscriptions";
////    }
//
//    @GetMapping("/view")
//    public String viewSubscriptions(Model model) {
//        model.addAttribute("subscriptions", subscriptionService.getAllSubscriptions());
//        return "subscriptions"; // Refers to subscriptions.html in /templates
//    }
//
////    // Serve subscriptions in JSON for testing or API consumers
////    @GetMapping("/test")
////    @ResponseBody
////    public List<SubscriptionDto> testSubscriptions() {
////        return subscriptionService.getAllSubscriptions();
////    }
//
//    @GetMapping("/test")
//    public List<SubscriptionDto> testSubscriptions() {
//        return subscriptionService.getAllSubscriptions();
//    }
//
//    @GetMapping
//    public List<SubscriptionDto> getAllSubscriptions() {
//        return subscriptionService.getAllSubscriptions();
//    }
//
////    @GetMapping("/{subscriptionId}")
////    public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable int subscriptionId) {
////        return ResponseEntity.ok(subscriptionService.getSubscriptionById(subscriptionId));
////    }
//
//    @GetMapping("/{subscriptionId}")
//    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable int subscriptionId)
//    {
//        return subscriptionRepository.findById(subscriptionId)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//
//    @PostMapping("/users/{userId}")
//    public ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto subscriptionDto, @PathVariable int userId) {
//        return ResponseEntity.ok(subscriptionService.createSubscription(userId, subscriptionDto));
//    }
//
//    @PutMapping("/{subscriptionId}")
//    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable int subscriptionId, @RequestBody SubscriptionDto subscriptionDto) {
//        return ResponseEntity.ok(subscriptionService.updateSubscription(subscriptionId, subscriptionDto));
//    }
//
//    @DeleteMapping("/{subscriptionId}")
//    public ResponseEntity<Void> deleteSubscription(@PathVariable int subscriptionId) {
//        subscriptionService.deleteSubscription(subscriptionId);
//        return ResponseEntity.noContent().build();
//    }
//}

//package com.netflix.api.netflix.controllers;
//
//import com.netflix.api.dto.SubscriptionDto;
//import com.netflix.api.dto.SubscriptionResponse;
//import com.netflix.api.netflix.dto.SubscriptionResponse;
//import com.netflix.api.netflix.services.SubscriptionService;
//import com.netflix.api.netflix.services.impl.SubscriptionServiceImpl;
//import com.netflix.api.services.SubscriptionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/netflix/")
//public class SubscriptionController {
//
//    private final SubscriptionService subscriptionService;
//
//    @Autowired
//    public SubscriptionController(SubscriptionService subscriptionService) {
//        this.subscriptionService = subscriptionService;
//    }
//
//    // Get all subscriptions with pagination
//    @GetMapping("subscriptions")
//    public ResponseEntity<SubscriptionResponse> getSubscriptions(
//            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
//            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
//    ) {
//        return new ResponseEntity<>(subscriptionService.getAllSubscriptions(pageNo, pageSize), HttpStatus.OK);
//    }
//
//    // Get subscription details by ID
//    @GetMapping("subscriptions/{id}")
//    public ResponseEntity<SubscriptionDto> getSubscriptionDetail(@PathVariable int id) {
//        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
//    }
//
//    // Create a new subscription
//    @PostMapping("subscriptions/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
//        return new ResponseEntity<>(subscriptionService.createSubscription(subscriptionDto), HttpStatus.CREATED);
//    }
//
//    // Update an existing subscription
//    @PutMapping("subscriptions/{id}/update")
//    public ResponseEntity<SubscriptionDto> updateSubscription(
//            @RequestBody SubscriptionDto subscriptionDto,
//            @PathVariable("id") int subscriptionId
//    ) {
//        SubscriptionDto updatedSubscription = subscriptionService.updateSubscription(subscriptionDto, subscriptionId);
//        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
//    }
//
//    // Delete a subscription by ID
//    @DeleteMapping("subscriptions/{id}/delete")
//    public ResponseEntity<String> deleteSubscription(@PathVariable("id") int subscriptionId) {
//        subscriptionService.deleteSubscriptionById(subscriptionId);
//        return new ResponseEntity<>("Subscription deleted successfully", HttpStatus.OK);
//    }
//}

