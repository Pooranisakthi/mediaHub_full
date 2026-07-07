package com.mediahub.notification.dto.request;

import com.mediahub.notification.entity.Notification;

public class NotificationRequestDTO {

    private Long userId;
    private String message;
    private Notification.Category category;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) {
        this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) {
        this.message = message; }

    public Notification.Category getCategory() {
        return category; }
    public void setCategory(
            Notification.Category category) {
        this.category = category; }
}