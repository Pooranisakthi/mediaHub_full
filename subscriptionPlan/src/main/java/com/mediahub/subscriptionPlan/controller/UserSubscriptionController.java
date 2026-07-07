package com.mediahub.subscriptionPlan.controller;

import com.mediahub.subscriptionPlan.dto.CreateSubscriptionRequest;
import com.mediahub.subscriptionPlan.dto.UpdateSubscriptionRequest;
import com.mediahub.subscriptionPlan.model.UserSubscription;
import com.mediahub.subscriptionPlan.service.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mediaHub/subscriptionPlan/usersubscriptions")
public class UserSubscriptionController {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @PostMapping("/createSubscription")
    public ResponseEntity<?> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userSubscriptionService.createSubscription(request));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Missing or invalid fields"));
        }
    }

    @GetMapping("/fetchSubscriptions")
    public ResponseEntity<?> fetchSubscriptions() {
        try {
            List<UserSubscription> list = userSubscriptionService.fetchSubscriptions();
            return ResponseEntity.ok(list);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No subscriptions found"));
        }
    }

    @GetMapping("/fetchSubscription/{subscriptionId}")
    public ResponseEntity<?> fetchSubscriptionById(@PathVariable Long subscriptionId) {
        try {
            return ResponseEntity.ok(userSubscriptionService.fetchSubscriptionById(subscriptionId));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Subscription not found"));
        }
    }

    @PutMapping("/updateSubscription/{subscriptionId}")
    public ResponseEntity<?> updateSubscription(@PathVariable Long subscriptionId,
                                                 @RequestBody UpdateSubscriptionRequest request) {
        try {
            return ResponseEntity.ok(userSubscriptionService.updateSubscription(subscriptionId, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", ex.getMessage()));
        }
    }
    
 // PUT - renew
    @PutMapping("/renewSubscription/{subscriptionId}")
    public ResponseEntity<?> renewSubscription(@PathVariable Long subscriptionId,
                                               @RequestBody UpdateSubscriptionRequest request) {
        try {
            return ResponseEntity.ok(userSubscriptionService.renewSubscription(subscriptionId, request));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", ex.getMessage()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    // PUT - cancel
    @PutMapping("/cancelSubscription/{subscriptionId}")
    public ResponseEntity<?> cancelSubscription(@PathVariable Long subscriptionId) {
        try {
            return ResponseEntity.ok(userSubscriptionService.cancelSubscription(subscriptionId));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", ex.getMessage()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", ex.getMessage()));
        }
    }
}