package com.mediahub.editorial.service;

import com.mediahub.editorial.model.EditorialReview;
import com.mediahub.editorial.repository.EditorialReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EditorialReviewService {

    @Autowired
    private EditorialReviewRepository repository;

    // API 1 — Submit review
    public Map<String, Object> submitReview(
            EditorialReview review) {
        Map<String, Object> response = new HashMap<>();
        if (review.getContentID() == 0) {
            response.put("error", "ContentID is required");
            response.put("statusCode", 400);
            return response;
        }
        if (review.getReviewerID() == 0) {
            response.put("error", "ReviewerID is required");
            response.put("statusCode", 400);
            return response;
        }
        if (review.getSubmissionDate() == null)
            review.setSubmissionDate(new Date());
        review.setStatus("Pending");
        int result = repository.save(review);
        if (result > 0) {
            response.put("contentID",      review.getContentID());
            response.put("reviewerID",     review.getReviewerID());
            response.put("submissionDate", review.getSubmissionDate());
            response.put("status",         "Pending");
            response.put("message",
                "Content submitted for review successfully");
            response.put("statusCode", 201);
        } else {
            response.put("error", "Failed to submit review");
            response.put("statusCode", 500);
        }
        return response;
    }

    // API 2 — Get all reviews
    public List<EditorialReview> getAllReviews() {
        return repository.findAll();
    }

    // API 3 — Get review by ID
    public Map<String, Object> getReviewById(int reviewID) {
        Map<String, Object> response = new HashMap<>();
        try {
            EditorialReview review = repository.findById(reviewID);
            response.put("review", review);
            response.put("statusCode", 200);
        } catch (Exception e) {
            response.put("error", "Review not found with ID: "
                    + reviewID);
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 4 — Approve review
    public Map<String, Object> approveReview(
            int reviewID, String remarks) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateDecision(
                    reviewID, "Approved", remarks, "Completed");
            if (result > 0) {
                response.put("reviewID",  reviewID);
                response.put("decision",  "Approved");
                response.put("status",    "Completed");
                response.put("message",
                    "Content approved successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Review not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Review not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 5 — Reject review
    public Map<String, Object> rejectReview(
            int reviewID, String remarks) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateDecision(
                    reviewID, "Rejected", remarks, "Completed");
            if (result > 0) {
                response.put("reviewID",  reviewID);
                response.put("decision",  "Rejected");
                response.put("status",    "Completed");
                response.put("message",
                    "Content rejected. Creator notified.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Review not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Review not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 6 — Request revision
    public Map<String, Object> requestRevision(
            int reviewID, String remarks) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateDecision(
                    reviewID, "RevisionRequired",
                    remarks, "Pending");
            if (result > 0) {
                response.put("reviewID",  reviewID);
                response.put("decision",  "RevisionRequired");
                response.put("status",    "Pending");
                response.put("message",
                    "Revision requested. Creator notified.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Review not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Review not found");
            response.put("statusCode", 404);
        }
        return response;
    }
}