package com.mediahub.royalty.model;

import java.util.Date;

public class RoyaltyPayout {

    private int payoutID;
    private int statementID;
    private int creatorID;
    private double amount;
    private Date payoutDate;
    private String method;
    private String status;

    public RoyaltyPayout() {
        this.status = "Pending";
    }

    public int getPayoutID()            { return payoutID; }
    public int getStatementID()         { return statementID; }
    public int getCreatorID()           { return creatorID; }
    public double getAmount()           { return amount; }
    public Date getPayoutDate()         { return payoutDate; }
    public String getMethod()           { return method; }
    public String getStatus()           { return status; }

    public void setPayoutID(int payoutID)
        { this.payoutID = payoutID; }
    public void setStatementID(int statementID)
        { this.statementID = statementID; }
    public void setCreatorID(int creatorID)
        { this.creatorID = creatorID; }
    public void setAmount(double amount)
        { this.amount = amount; }
    public void setPayoutDate(Date payoutDate)
        { this.payoutDate = payoutDate; }
    public void setMethod(String method)
        { this.method = method; }
    public void setStatus(String status)
        { this.status = status; }
}
