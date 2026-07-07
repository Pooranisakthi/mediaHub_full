package com.mediahub.royalty.service;

import com.mediahub.royalty.model.RoyaltyPayout;
import com.mediahub.royalty.repository.RoyaltyPayoutRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoyaltyPayoutService {

    private final RoyaltyPayoutRepository repository;

    public RoyaltyPayoutService(RoyaltyPayoutRepository repository) {
        this.repository = repository;
    }

    // API 11 — Create payout
    public Map<String, Object> createPayout(RoyaltyPayout payout) {
        Map<String, Object> response = new HashMap<>();
        if (payout.getStatementID() == 0) {
            response.put("error", "StatementID is required");
            response.put("statusCode", 400);
            return response;
        }
        if (payout.getCreatorID() == 0) {
            response.put("error", "CreatorID is required");
            response.put("statusCode", 400);
            return response;
        }
        if (payout.getAmount() <= 0) {
            response.put("error", "Amount must be greater than zero");
            response.put("statusCode", 400);
            return response;
        }
        String method = payout.getMethod();
        if (method == null || (!method.equals("BankTransfer")
                && !method.equals("WalletCredit"))) {
            response.put("error",
                "Method must be BankTransfer or WalletCredit");
            response.put("statusCode", 400);
            return response;
        }
        if (payout.getPayoutDate() == null)
            payout.setPayoutDate(new Date());
        payout.setStatus("Pending");
        int result = repository.save(payout);
        if (result > 0) {
            response.put("statementID", payout.getStatementID());
            response.put("creatorID",   payout.getCreatorID());
            response.put("amount",      payout.getAmount());
            response.put("payoutDate",  payout.getPayoutDate());
            response.put("method",      payout.getMethod());
            response.put("status",      "Pending");
            response.put("message",     "Payout created successfully.");
            response.put("statusCode",  201);
        } else {
            response.put("error", "Failed to create payout");
            response.put("statusCode", 500);
        }
        return response;
    }

    // API 12 — Get all payouts
    public List<RoyaltyPayout> getAllPayouts() {
        return repository.findAll();
    }

    // API 13 — Get payout by ID
    public Map<String, Object> getPayoutById(int payoutID) {
        Map<String, Object> response = new HashMap<>();
        try {
            RoyaltyPayout p = repository.findById(payoutID);
            response.put("payout",     p);
            response.put("statusCode", 200);
        } catch (Exception e) {
            response.put("error",
                "Payout not found with ID: " + payoutID);
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 14 — Process payout
    public Map<String, Object> processPayout(int payoutID) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateStatus(payoutID, "Processed");
            if (result > 0) {
                response.put("payoutID", payoutID);
                response.put("status",   "Processed");
                response.put("message",  "Payout processed successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Payout not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Payout not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 15 — Mark payout as Failed
    public Map<String, Object> failPayout(int payoutID, String reason) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateStatus(payoutID, "Failed");
            if (result > 0) {
                response.put("payoutID", payoutID);
                response.put("status",   "Failed");
                response.put("reason",   reason);
                response.put("message",  "Payout marked as failed.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Payout not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Payout not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 16 — Delete payout (only if Pending)
    public Map<String, Object> deletePayout(int payoutID) {
        Map<String, Object> response = new HashMap<>();
        try {
            String status = repository.findStatusById(payoutID);
            if (status.equals("Processed")) {
                response.put("error",
                    "Cannot delete Processed payout.");
                response.put("statusCode", 400);
                return response;
            }
            int result = repository.delete(payoutID);
            if (result > 0) {
                response.put("payoutID", payoutID);
                response.put("message",  "Payout deleted successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Payout not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Payout not found");
            response.put("statusCode", 404);
        }
        return response;
    }
}
