package com.mediahub.royalty.model;

import java.util.Date;

public class RoyaltyRule {

    private int ruleID;
    private String creatorTier;
    private double revenueSharePercent;
    private double minimumPayoutThreshold;
    private String payoutFrequency;
    private Date effectiveDate;
    private String status;

    public RoyaltyRule() {
        this.status = "Active";
    }

    public int getRuleID()                      { return ruleID; }
    public String getCreatorTier()              { return creatorTier; }
    public double getRevenueSharePercent()      { return revenueSharePercent; }
    public double getMinimumPayoutThreshold()   { return minimumPayoutThreshold; }
    public String getPayoutFrequency()          { return payoutFrequency; }
    public Date getEffectiveDate()              { return effectiveDate; }
    public String getStatus()                   { return status; }

    public void setRuleID(int ruleID)
        { this.ruleID = ruleID; }
    public void setCreatorTier(String creatorTier)
        { this.creatorTier = creatorTier; }
    public void setRevenueSharePercent(double revenueSharePercent)
        { this.revenueSharePercent = revenueSharePercent; }
    public void setMinimumPayoutThreshold(double minimumPayoutThreshold)
        { this.minimumPayoutThreshold = minimumPayoutThreshold; }
    public void setPayoutFrequency(String payoutFrequency)
        { this.payoutFrequency = payoutFrequency; }
    public void setEffectiveDate(Date effectiveDate)
        { this.effectiveDate = effectiveDate; }
    public void setStatus(String status)
        { this.status = status; }
}
