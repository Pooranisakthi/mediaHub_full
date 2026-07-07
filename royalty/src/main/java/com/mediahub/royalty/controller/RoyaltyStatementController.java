package com.mediahub.royalty.controller;

import com.mediahub.royalty.model.RoyaltyStatement;
import com.mediahub.royalty.service.RoyaltyStatementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/royalty-statements")
public class RoyaltyStatementController {

    private final RoyaltyStatementService service;

    public RoyaltyStatementController(RoyaltyStatementService service) {
        this.service = service;
    }

    // API 6 — Generate statement
    @PostMapping
    public ResponseEntity<Map<String, Object>> generateStatement(
            @RequestBody RoyaltyStatement statement) {
        Map<String, Object> response = service.generateStatement(statement);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 7 — Get all statements
    @GetMapping
    public ResponseEntity<List<RoyaltyStatement>> getAllStatements() {
        return ResponseEntity.ok(service.getAllStatements());
    }

    // API 8 — Get statement by ID
    @GetMapping("/{statementID}")
    public ResponseEntity<Map<String, Object>> getStatementById(
            @PathVariable int statementID) {
        Map<String, Object> response = service.getStatementById(statementID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 9 — Finalise statement
    @PutMapping("/{statementID}/finalise")
    public ResponseEntity<Map<String, Object>> finaliseStatement(
            @PathVariable int statementID) {
        Map<String, Object> response = service.finaliseStatement(statementID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }

    // API 10 — Mark as Paid
    @PutMapping("/{statementID}/mark-paid")
    public ResponseEntity<Map<String, Object>> markAsPaid(
            @PathVariable int statementID) {
        Map<String, Object> response = service.markAsPaid(statementID);
        int code = (int) response.get("statusCode");
        return ResponseEntity.status(code).body(response);
    }
}
