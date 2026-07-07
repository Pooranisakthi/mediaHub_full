package com.mediahub.notification.service;

import com.mediahub.notification.dto.request.NotificationRequestDTO;
import com.mediahub.notification.dto.response.NotificationResponseDTO;
import com.mediahub.notification.entity.Notification;
import com.mediahub.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(
            NotificationRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public NotificationResponseDTO createNotification(
            NotificationRequestDTO request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setMessage(request.getMessage());
        notification.setCategory(request.getCategory());
        notification.setStatus(
            Notification.Status.UNREAD);
        notification.setCreatedDate(LocalDateTime.now());
        return toResponse(repository.save(notification));
    }

    // GET ALL BY USER ID
    public List<NotificationResponseDTO>
            getAllNotifications(Long userId) {
        return repository.findByUserId(userId)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    // GET UNREAD BY USER ID
    public List<NotificationResponseDTO>
            getUnreadNotifications(Long userId) {
        return repository.findByUserIdAndStatus(
                userId, Notification.Status.UNREAD)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    // UPDATE
    public NotificationResponseDTO updateNotification(
            Long id, String status) {
        Notification notification = repository
            .findById(id)
            .orElseThrow(() ->
                new NoSuchElementException(
                    "Notification not found with id "
                    + id));

        // Business Rule
        if (notification.getStatus() ==
                Notification.Status.DISMISSED) {
            throw new RuntimeException(
                "Dismissed notification " +
                "cannot be updated");
        }

        notification.setStatus(
            Notification.Status.valueOf(
                status.toUpperCase()));
        return toResponse(repository.save(notification));
    }

    // Convert Entity to ResponseDTO
    private NotificationResponseDTO toResponse(
            Notification notification) {
        NotificationResponseDTO dto =
            new NotificationResponseDTO();
        dto.setNotificationId(
            notification.getNotificationId());
        dto.setUserId(notification.getUserId());
        dto.setMessage(notification.getMessage());
        dto.setCategory(notification.getCategory());
        dto.setStatus(notification.getStatus());
        dto.setCreatedDate(
            notification.getCreatedDate());
        return dto;
    }
}