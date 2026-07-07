package com.mediahub.subscriptionPlan.service;

import com.mediahub.subscriptionPlan.dto.CreateSubscriptionRequest;
import com.mediahub.subscriptionPlan.dto.UpdateSubscriptionRequest;
import com.mediahub.subscriptionPlan.model.UserSubscription;
import com.mediahub.subscriptionPlan.repository.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSubscriptionService {

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    // POST
    public Map<String, String> createSubscription(CreateSubscriptionRequest request) {
        Map<String, String> response = new HashMap<>();
        userSubscriptionRepository.findByUserIdAndStatus(request.getUserId(), "Active")
                .ifPresent(existing -> {
                    throw new IllegalStateException("Active subscription already exists");
                });
        UserSubscription subscription = UserSubscription.builder()
                .userId(request.getUserId())
                .planId(request.getPlanId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .renewalType(request.getRenewalType())
                .build();
        userSubscriptionRepository.save(subscription);
        response.put("message", "Subscription created successfully");
        return response;
    }

    // GET all
    public List<UserSubscription> fetchSubscriptions() {
        return userSubscriptionRepository.findAll();
    }

    // GET by ID
    public UserSubscription fetchSubscriptionById(Long subscriptionId) {
        return userSubscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    // PUT - update
    public Map<String, String> updateSubscription(Long subscriptionId, UpdateSubscriptionRequest request) {
        Map<String, String> response = new HashMap<>();
        UserSubscription subscription = userSubscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        if (request.getPlanId() != null) subscription.setPlanId(request.getPlanId());
        if (request.getEndDate() != null) subscription.setEndDate(request.getEndDate());
        if (request.getRenewalType() != null) subscription.setRenewalType(request.getRenewalType());
        userSubscriptionRepository.save(subscription);
        response.put("message", "Subscription updated successfully");
        return response;
    }

    // PUT - renew
    public Map<String, String> renewSubscription(Long subscriptionId, UpdateSubscriptionRequest request) {
        Map<String, String> response = new HashMap<>();
        UserSubscription subscription = userSubscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        if (!subscription.getStatus().equals("Active")) {
            throw new IllegalStateException("Subscription not eligible for renewal");
        }
        if (request.getEndDate() != null) subscription.setEndDate(request.getEndDate());
        if (request.getRenewalType() != null) subscription.setRenewalType(request.getRenewalType());
        userSubscriptionRepository.save(subscription);
        response.put("message", "Subscription renewed successfully");
        return response;
    }
    

    // PUT - cancel
    public Map<String, String> cancelSubscription(Long subscriptionId) {
        Map<String, String> response = new HashMap<>();
        UserSubscription subscription = userSubscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        if (subscription.getStatus().equals("Cancelled")) {
            throw new IllegalStateException("Subscription already cancelled");
        }
        subscription.setStatus("Cancelled");
        userSubscriptionRepository.save(subscription);
        response.put("message", "Subscription cancelled successfully");
        return response;
    }
}