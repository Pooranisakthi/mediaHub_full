package com.mediahub.subscriptionPlan.service;

import com.mediahub.subscriptionPlan.dto.CreateHistoryRequest;
import com.mediahub.subscriptionPlan.dto.UpdateHistoryRequest;
import com.mediahub.subscriptionPlan.model.SubscriptionHistory;
import com.mediahub.subscriptionPlan.repository.SubscriptionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionHistoryService {

    @Autowired
    private SubscriptionHistoryRepository subscriptionHistoryRepository;

    // POST
    public Map<String, String> createHistory(CreateHistoryRequest request) {
        Map<String, String> response = new HashMap<>();
        subscriptionHistoryRepository
                .findBySubscriptionIdAndChangeType(request.getSubscriptionId(), request.getChangeType())
                .ifPresent(existing -> {
                    throw new IllegalStateException("History already exists");
                });
        SubscriptionHistory history = SubscriptionHistory.builder()
                .subscriptionId(request.getSubscriptionId())
                .userId(request.getUserId())
                .fromPlanId(request.getFromPlanId())
                .toPlanId(request.getToPlanId())
                .changeType(request.getChangeType())
                .changeDate(request.getChangeDate())
                .remarks(request.getRemarks())
                .build();
        subscriptionHistoryRepository.save(history);
        response.put("message", "History created successfully");
        return response;
    }

    // GET all
    public List<SubscriptionHistory> fetchHistories() {
        return subscriptionHistoryRepository.findAll();
    }

    // GET by ID
    public SubscriptionHistory fetchHistoryById(Long historyId) {
        return subscriptionHistoryRepository.findById(historyId)
                .orElseThrow(() -> new RuntimeException("History not found"));
    }

    // PUT
    public Map<String, String> updateHistory(Long historyId, UpdateHistoryRequest request) {
        Map<String, String> response = new HashMap<>();
        SubscriptionHistory history = subscriptionHistoryRepository.findById(historyId)
                .orElseThrow(() -> new RuntimeException("History not found"));
        if (request.getRemarks() != null) history.setRemarks(request.getRemarks());
        if (request.getChangeDate() != null) history.setChangeDate(request.getChangeDate());
        subscriptionHistoryRepository.save(history);
        response.put("message", "History updated successfully");
        return response;
    }
}