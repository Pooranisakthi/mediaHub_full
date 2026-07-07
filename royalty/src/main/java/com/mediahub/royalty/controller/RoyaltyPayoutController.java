package com.mediahub.royalty.controller;

import com.mediahub.royalty.model.RoyaltyPayout;
import com.mediahub.royalty.service.RoyaltyPayoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/royalty-payouts")
public class RoyaltyPayoutController {

    private final RoyaltyPayoutService service;

    public RoyaltyPayoutController(RoyaltyPayoutService service) {
        this.service = service;
    }

    // API 11 — Create payout
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPayout(
            @RequestBody RoyaltyPayout payout) {
        Map<String, Object> response = service.createPayout(payout);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 12 — Get all payouts
    @GetMapping
    public ResponseEntity<List<RoyaltyPayout>> getAllPayouts() {
        return ResponseEntity.ok(service.getAllPayouts());
    }

    // API 13 — Get payout by ID
    @GetMapping("/{payoutID}")
    public ResponseEntity<Map<String, Object>> getPayoutById(
            @PathVariable int payoutID) {
        Map<String, Object> response = service.getPayoutById(payoutID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 14 — Process payout
    @PutMapping("/{payoutID}/process")
    public ResponseEntity<Map<String, Object>> processPayout(
            @PathVariable int payoutID) {
        Map<String, Object> response = service.processPayout(payoutID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 15 — Fail payout
    @PutMapping("/{payoutID}/fail")
    public ResponseEntity<Map<String, Object>> failPayout(
            @PathVariable int payoutID,
            @RequestParam String reason) {
        Map<String, Object> response = service.failPayout(payoutID, reason);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 16 — Delete payout
    @DeleteMapping("/{payoutID}")
    public ResponseEntity<Map<String, Object>> deletePayout(
            @PathVariable int payoutID) {
        Map<String, Object> response = service.deletePayout(payoutID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }
}
