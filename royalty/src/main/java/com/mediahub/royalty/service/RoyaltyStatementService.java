package com.mediahub.royalty.service;

import com.mediahub.royalty.model.RoyaltyStatement;
import com.mediahub.royalty.repository.RoyaltyStatementRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoyaltyStatementService {

    private final RoyaltyStatementRepository repository;

    public RoyaltyStatementService(RoyaltyStatementRepository repository) {
        this.repository = repository;
    }

    // API 6 — Generate statement
    public Map<String, Object> generateStatement(RoyaltyStatement statement) {
        Map<String, Object> response = new HashMap<>();
        if (statement.getCreatorID() == 0) {
            response.put("error", "CreatorID is required");
            response.put("statusCode", 400);
            return response;
        }
        if (statement.getPeriod() == null || statement.getPeriod().isEmpty()) {
            response.put("error", "Period is required");
            response.put("statusCode", 400);
            return response;
        }
        if (statement.getTotalRevenue() < 0) {
            response.put("error", "TotalRevenue cannot be negative");
            response.put("statusCode", 400);
            return response;
        }
        statement.setStatus("Draft");
        int result = repository.save(statement);
        if (result > 0) {
            response.put("creatorID",     statement.getCreatorID());
            response.put("period",        statement.getPeriod());
            response.put("totalViews",    statement.getTotalViews());
            response.put("totalRevenue",  statement.getTotalRevenue());
            response.put("royaltyAmount", statement.getRoyaltyAmount());
            response.put("status",        "Draft");
            response.put("message",       "Royalty statement generated successfully.");
            response.put("statusCode",    201);
        } else {
            response.put("error", "Failed to generate statement");
            response.put("statusCode", 500);
        }
        return response;
    }

    // API 7 — Get all statements
    public List<RoyaltyStatement> getAllStatements() {
        return repository.findAll();
    }

    // API 8 — Get statement by ID
    public Map<String, Object> getStatementById(int statementID) {
        Map<String, Object> response = new HashMap<>();
        try {
            RoyaltyStatement s = repository.findById(statementID);
            response.put("statement", s);
            response.put("statusCode", 200);
        } catch (Exception e) {
            response.put("error",
                "Statement not found with ID: " + statementID);
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 9 — Finalise statement
    public Map<String, Object> finaliseStatement(int statementID) {
        Map<String, Object> response = new HashMap<>();
        try {
            String currentStatus = repository.findStatusById(statementID);
            if (!currentStatus.equals("Draft")) {
                response.put("error",
                    "Only Draft statements can be finalised.");
                response.put("statusCode", 400);
                return response;
            }
            int result = repository.updateStatus(statementID, "Finalised");
            if (result > 0) {
                response.put("statementID", statementID);
                response.put("status",      "Finalised");
                response.put("message",     "Statement finalised successfully.");
                response.put("statusCode",  200);
            } else {
                response.put("error", "Statement not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Statement not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 10 — Mark statement as Paid
    public Map<String, Object> markAsPaid(int statementID) {
        Map<String, Object> response = new HashMap<>();
        try {
            String currentStatus = repository.findStatusById(statementID);
            if (!currentStatus.equals("Finalised")) {
                response.put("error",
                    "Only Finalised statements can be marked as Paid.");
                response.put("statusCode", 400);
                return response;
            }
            int result = repository.updateStatus(statementID, "Paid");
            if (result > 0) {
                response.put("statementID", statementID);
                response.put("status",      "Paid");
                response.put("message",     "Statement marked as Paid successfully.");
                response.put("statusCode",  200);
            } else {
                response.put("error", "Statement not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Statement not found");
            response.put("statusCode", 404);
        }
        return response;
    }
}
