package com.mediahub.notification.controller;

import com.mediahub.notification.dto.request.NotificationRequestDTO;
import com.mediahub.notification.dto.response.NotificationResponseDTO;
import com.mediahub.notification.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mediaHub/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(
            NotificationService service) {
        this.service = service;
    }

    // POST — Create notification
    @PostMapping("/createNotification/v1.0")
    public ResponseEntity<NotificationResponseDTO>
            createNotification(
                @RequestBody
                NotificationRequestDTO request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.createNotification(request));
    }

    // GET — All notifications for a user
    @GetMapping("/getAllNotifications/v1.0/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>>
            getAllNotifications(
                @PathVariable Long userId) {
        return ResponseEntity.ok(
            service.getAllNotifications(userId));
    }

    // GET — Unread notifications for a user
    @GetMapping(
        "/getUnreadNotifications/v1.0/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>>
            getUnreadNotifications(
                @PathVariable Long userId) {
        return ResponseEntity.ok(
            service.getUnreadNotifications(userId));
    }

    // PUT — Update notification status
    @PutMapping("/updateNotification/v1.0/{id}")
    public ResponseEntity<?> updateNotification(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(
            service.updateNotification(id, status));
    }

    // Exception handler
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(
            RuntimeException e) {
        return ResponseEntity.status(400)
            .body("{\"error\": \"" +
                  e.getMessage() + "\"}");
    }
}