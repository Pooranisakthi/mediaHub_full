package com.mediahub.editorial.model;

import java.util.Date;

public class EditorialReview {

    private int reviewID;
    private int contentID;
    private int reviewerID;
    private Date submissionDate;
    private Date reviewDate;
    private String decision;
    private String remarks;
    private String status;

    // Default constructor
    public EditorialReview() {
        this.status = "Pending";
    }

    // Getters
    public int getReviewID()            { return reviewID; }
    public int getContentID()           { return contentID; }
    public int getReviewerID()          { return reviewerID; }
    public Date getSubmissionDate()     { return submissionDate; }
    public Date getReviewDate()         { return reviewDate; }
    public String getDecision()         { return decision; }
    public String getRemarks()          { return remarks; }
    public String getStatus()           { return status; }

    // Setters
    public void setReviewID(int reviewID)
        { this.reviewID = reviewID; }
    public void setContentID(int contentID)
        { this.contentID = contentID; }
    public void setReviewerID(int reviewerID)
        { this.reviewerID = reviewerID; }
    public void setSubmissionDate(Date submissionDate)
        { this.submissionDate = submissionDate; }
    public void setReviewDate(Date reviewDate)
        { this.reviewDate = reviewDate; }
    public void setDecision(String decision)
        { this.decision = decision; }
    public void setRemarks(String remarks)
        { this.remarks = remarks; }
    public void setStatus(String status)
        { this.status = status; }
}