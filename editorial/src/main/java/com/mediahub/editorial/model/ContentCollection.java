package com.mediahub.editorial.model;

import java.util.Date;
import java.util.List;

public class ContentCollection {

    private int collectionID;
    private String name;
    private String category;
    private List<Integer> contentIDs;
    private Date publishDate;
    private Date expiryDate;
    private String status;

    // Default constructor
    // Status is always Scheduled when collection is created
    public ContentCollection() {
        this.status = "Scheduled";
    }

    // Getters
    public int getCollectionID() {
        return collectionID;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<Integer> getContentIDs() {
        return contentIDs;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    
    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setContentIDs(List<Integer> contentIDs) {
        this.contentIDs = contentIDs;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}