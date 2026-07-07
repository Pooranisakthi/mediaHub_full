package com.mediahub.subscriptionPlan.service;

import com.mediahub.subscriptionPlan.dto.CreatePlanRequest;
import com.mediahub.subscriptionPlan.dto.UpdatePlanRequest;
import com.mediahub.subscriptionPlan.model.SubscriptionPlan;
import com.mediahub.subscriptionPlan.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    // POST
    public Map<String, String> createPlan(CreatePlanRequest request) {
        Map<String, String> response = new HashMap<>();
        subscriptionPlanRepository.findByName(request.getName()).ifPresent(existing -> {
            throw new IllegalStateException("Plan already exists");
        });
        SubscriptionPlan plan = SubscriptionPlan.builder()
                .name(request.getName())
                .price(request.getPrice())
                .billingCycle(request.getBillingCycle())
                .contentAccessLevel(request.getContentAccessLevel())
                .maxDevices(request.getMaxDevices())
                .downloadAllowed(request.getDownloadAllowed())
                .build();
        subscriptionPlanRepository.save(plan);
        response.put("message", "Plan created successfully");
        return response;
    }

    // GET all
    public List<SubscriptionPlan> fetchPlans() {
        return subscriptionPlanRepository.findAll();
    }

    // GET by ID
    public SubscriptionPlan fetchPlanById(Long planId) {
        return subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    // PUT
    public Map<String, String> updatePlan(Long planId, UpdatePlanRequest request) {
        Map<String, String> response = new HashMap<>();
        SubscriptionPlan plan = subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        if (request.getPrice() != null) plan.setPrice(request.getPrice());
        if (request.getBillingCycle() != null) plan.setBillingCycle(request.getBillingCycle());
        if (request.getMaxDevices() != null) plan.setMaxDevices(request.getMaxDevices());
        if (request.getDownloadAllowed() != null) plan.setDownloadAllowed(request.getDownloadAllowed());
        subscriptionPlanRepository.save(plan);
        response.put("message", "Plan updated successfully");
        return response;
    }
}