package com.mediahub.notification.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationId")
    private Long notificationId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "message", nullable = false,
            length = 2000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    public Notification() {}

    public Long getNotificationId() {
        return notificationId; }
    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) {
        this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) {
        this.message = message; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) {
        this.category = category; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) {
        this.status = status; }

    public LocalDateTime getCreatedDate() {
        return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate; }

    public enum Category {
        CONTENT, SUBSCRIPTION, ROYALTY,
        LICENSE, EDITORIAL
    }

    public enum Status {
        UNREAD, READ, DISMISSED
    }
}