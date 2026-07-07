package com.mediahub.royalty.model;

public class RoyaltyStatement {

    private int statementID;
    private int creatorID;
    private String period;
    private long totalViews;
    private double totalRevenue;
    private double royaltyAmount;
    private String status;

    public RoyaltyStatement() {
        this.status = "Draft";
    }

    public int getStatementID()         { return statementID; }
    public int getCreatorID()           { return creatorID; }
    public String getPeriod()           { return period; }
    public long getTotalViews()         { return totalViews; }
    public double getTotalRevenue()     { return totalRevenue; }
    public double getRoyaltyAmount()    { return royaltyAmount; }
    public String getStatus()           { return status; }

    public void setStatementID(int statementID)
        { this.statementID = statementID; }
    public void setCreatorID(int creatorID)
        { this.creatorID = creatorID; }
    public void setPeriod(String period)
        { this.period = period; }
    public void setTotalViews(long totalViews)
        { this.totalViews = totalViews; }
    public void setTotalRevenue(double totalRevenue)
        { this.totalRevenue = totalRevenue; }
    public void setRoyaltyAmount(double royaltyAmount)
        { this.royaltyAmount = royaltyAmount; }
    public void setStatus(String status)
        { this.status = status; }
}
