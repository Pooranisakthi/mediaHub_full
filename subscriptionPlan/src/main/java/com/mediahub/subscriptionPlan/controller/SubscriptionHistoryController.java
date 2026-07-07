package com.mediahub.subscriptionPlan.controller;

import com.mediahub.subscriptionPlan.dto.CreateHistoryRequest;
import com.mediahub.subscriptionPlan.dto.UpdateHistoryRequest;
import com.mediahub.subscriptionPlan.model.SubscriptionHistory;
import com.mediahub.subscriptionPlan.service.SubscriptionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mediaHub/subscriptionPlan/subscriptionhistory")
public class SubscriptionHistoryController {

    @Autowired
    private SubscriptionHistoryService subscriptionHistoryService;

    @PostMapping("/createHistory")
    public ResponseEntity<?> createHistory(@RequestBody CreateHistoryRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(subscriptionHistoryService.createHistory(request));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Missing or invalid fields"));
        }
    }

    @GetMapping("/fetchHistories")
    public ResponseEntity<?> fetchHistories() {
        try {
            List<SubscriptionHistory> list = subscriptionHistoryService.fetchHistories();
            return ResponseEntity.ok(list);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No history found"));
        }
    }

    @GetMapping("/fetchHistory/{historyId}")
    public ResponseEntity<?> fetchHistoryById(@PathVariable Long historyId) {
        try {
            return ResponseEntity.ok(subscriptionHistoryService.fetchHistoryById(historyId));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "History not found"));
        }
    }

    @PutMapping("/updateHistory/{historyId}")
    public ResponseEntity<?> updateHistory(@PathVariable Long historyId,
                                            @RequestBody UpdateHistoryRequest request) {
        try {
            return ResponseEntity.ok(subscriptionHistoryService.updateHistory(historyId, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", ex.getMessage()));
        }
    }
}