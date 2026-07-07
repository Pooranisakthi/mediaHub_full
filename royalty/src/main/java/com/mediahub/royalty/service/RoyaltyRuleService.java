package com.mediahub.royalty.service;

import com.mediahub.royalty.model.RoyaltyRule;
import com.mediahub.royalty.repository.RoyaltyRuleRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoyaltyRuleService {

    private final RoyaltyRuleRepository repository;

    public RoyaltyRuleService(RoyaltyRuleRepository repository) {
        this.repository = repository;
    }

    // API 1 — Create royalty rule
    public Map<String, Object> createRule(RoyaltyRule rule) {
        Map<String, Object> response = new HashMap<>();
        if (rule.getCreatorTier() == null || rule.getCreatorTier().isEmpty()) {
            response.put("error", "CreatorTier is required");
            response.put("statusCode", 400);
            return response;
        }
        if (rule.getRevenueSharePercent() <= 0
                || rule.getRevenueSharePercent() > 100) {
            response.put("error",
                "RevenueSharePercent must be between 1 and 100");
            response.put("statusCode", 400);
            return response;
        }
        String freq = rule.getPayoutFrequency();
        if (freq == null || (!freq.equals("Monthly")
                && !freq.equals("Quarterly"))) {
            response.put("error",
                "PayoutFrequency must be Monthly or Quarterly");
            response.put("statusCode", 400);
            return response;
        }
        if (rule.getEffectiveDate() == null) {
            response.put("error", "EffectiveDate is required");
            response.put("statusCode", 400);
            return response;
        }
        rule.setStatus("Active");
        int result = repository.save(rule);
        if (result > 0) {
            response.put("creatorTier",             rule.getCreatorTier());
            response.put("revenueSharePercent",     rule.getRevenueSharePercent());
            response.put("minimumPayoutThreshold",  rule.getMinimumPayoutThreshold());
            response.put("payoutFrequency",         rule.getPayoutFrequency());
            response.put("effectiveDate",           rule.getEffectiveDate());
            response.put("status",                  "Active");
            response.put("message",
                "Royalty rule created successfully.");
            response.put("statusCode", 201);
        } else {
            response.put("error", "Failed to create royalty rule");
            response.put("statusCode", 500);
        }
        return response;
    }

    // API 2 — Get all rules
    public List<RoyaltyRule> getAllRules() {
        return repository.findAll();
    }

    // API 3 — Get rule by ID
    public Map<String, Object> getRuleById(int ruleID) {
        Map<String, Object> response = new HashMap<>();
        try {
            RoyaltyRule rule = repository.findById(ruleID);
            response.put("rule", rule);
            response.put("statusCode", 200);
        } catch (Exception e) {
            response.put("error", "Royalty rule not found with ID: " + ruleID);
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 4 — Deactivate rule
    public Map<String, Object> deactivateRule(int ruleID) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateStatus(ruleID, "Inactive");
            if (result > 0) {
                response.put("ruleID",  ruleID);
                response.put("status",  "Inactive");
                response.put("message", "Royalty rule deactivated successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Royalty rule not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Royalty rule not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 5 — Delete rule (only if Inactive)
    public Map<String, Object> deleteRule(int ruleID) {
        Map<String, Object> response = new HashMap<>();
        try {
            String status = repository.findStatusById(ruleID);
            if (status.equals("Active")) {
                response.put("error",
                    "Cannot delete Active royalty rule. Deactivate it first.");
                response.put("statusCode", 400);
                return response;
            }
            int result = repository.delete(ruleID);
            if (result > 0) {
                response.put("ruleID",  ruleID);
                response.put("message", "Royalty rule deleted successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Royalty rule not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Royalty rule not found");
            response.put("statusCode", 404);
        }
        return response;
    }
}
