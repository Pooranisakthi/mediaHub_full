package com.mediahub.royalty.controller;

import com.mediahub.royalty.model.RoyaltyRule;
import com.mediahub.royalty.service.RoyaltyRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/royalty-rules")
public class RoyaltyRuleController {

    private final RoyaltyRuleService service;

    public RoyaltyRuleController(RoyaltyRuleService service) {
        this.service = service;
    }

    // API 1 — Create royalty rule
    @PostMapping
    public ResponseEntity<Map<String, Object>> createRule(
            @RequestBody RoyaltyRule rule) {
        Map<String, Object> response = service.createRule(rule);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 2 — Get all rules
    @GetMapping
    public ResponseEntity<List<RoyaltyRule>> getAllRules() {
        return ResponseEntity.ok(service.getAllRules());
    }

    // API 3 — Get rule by ID
    @GetMapping("/{ruleID}")
    public ResponseEntity<Map<String, Object>> getRuleById(
            @PathVariable int ruleID) {
        Map<String, Object> response = service.getRuleById(ruleID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 4 — Deactivate rule
    @PutMapping("/{ruleID}/deactivate")
    public ResponseEntity<Map<String, Object>> deactivateRule(
            @PathVariable int ruleID) {
        Map<String, Object> response = service.deactivateRule(ruleID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 5 — Delete rule
    @DeleteMapping("/{ruleID}")
    public ResponseEntity<Map<String, Object>> deleteRule(
            @PathVariable int ruleID) {
        Map<String, Object> response = service.deleteRule(ruleID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }
}
