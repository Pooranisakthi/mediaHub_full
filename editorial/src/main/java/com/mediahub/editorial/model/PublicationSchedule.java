package com.mediahub.editorial.model;

import java.util.Date;

public class PublicationSchedule {

    private int scheduleID;
    private int contentID;
    private Date publishDateTime;
    private Date expiryDateTime;
    private String territory;
    private String status;

    // Default constructor
    // Status is always Scheduled when schedule is created
    public PublicationSchedule() {
        this.status = "Scheduled";
    }

    // Getters
    public int getScheduleID() {
        return scheduleID;
    }

    public int getContentID() {
        return contentID;
    }

    public Date getPublishDateTime() {
        return publishDateTime;
    }

    public Date getExpiryDateTime() {
        return expiryDateTime;
    }

    public String getTerritory() {
        return territory;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public void setPublishDateTime(Date publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    public void setExpiryDateTime(Date expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}