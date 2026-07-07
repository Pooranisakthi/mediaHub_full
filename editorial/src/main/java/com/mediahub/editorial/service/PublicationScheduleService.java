package com.mediahub.editorial.service;

import com.mediahub.editorial.model.PublicationSchedule;
import com.mediahub.editorial.repository.PublicationScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PublicationScheduleService {

    @Autowired
    private PublicationScheduleRepository repository;

    // API 13 — Create schedule
    public Map<String, Object> createSchedule(
            PublicationSchedule schedule) {
        Map<String, Object> response = new HashMap<>();
        if (schedule.getContentID() == 0) {
            response.put("error", "ContentID is required");
            response.put("statusCode", 400);
            return response;
        }
        if (schedule.getPublishDateTime() == null) {
            response.put("error", "PublishDateTime is required");
            response.put("statusCode", 400);
            return response;
        }
        if (schedule.getExpiryDateTime() == null) {
            response.put("error", "ExpiryDateTime is required");
            response.put("statusCode", 400);
            return response;
        }
        if (schedule.getTerritory() == null
                || schedule.getTerritory().isEmpty()) {
            response.put("error", "Territory is required");
            response.put("statusCode", 400);
            return response;
        }
        schedule.setStatus("Scheduled");
        int result = repository.save(schedule);
        if (result > 0) {
            response.put("contentID",
                schedule.getContentID());
            response.put("publishDateTime",
                schedule.getPublishDateTime());
            response.put("expiryDateTime",
                schedule.getExpiryDateTime());
            response.put("territory",  schedule.getTerritory());
            response.put("status",     "Scheduled");
            response.put("message",
                "Content scheduled successfully.");
            response.put("statusCode", 201);
        } else {
            response.put("error", "Failed to create schedule");
            response.put("statusCode", 500);
        }
        return response;
    }

    // API 14 — Get all schedules
    public List<PublicationSchedule> getAllSchedules() {
        return repository.findAll();
    }

    // API 15 — Get schedule by ID
    public Map<String, Object> getScheduleById(int scheduleID) {
        Map<String, Object> response = new HashMap<>();
        try {
            PublicationSchedule s =
                    repository.findById(scheduleID);
            response.put("schedule",    s);
            response.put("statusCode",  200);
        } catch (Exception e) {
            response.put("error",
                "Schedule not found with ID: " + scheduleID);
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 16 — Publish manually
    public Map<String, Object> publishSchedule(int scheduleID) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateStatus(
                    scheduleID, "Published");
            if (result > 0) {
                response.put("scheduleID", scheduleID);
                response.put("status",     "Published");
                response.put("message",
                    "Content published successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Schedule not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Schedule not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 17 — Cancel schedule
    public Map<String, Object> cancelSchedule(
            int scheduleID, String reason) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateStatus(
                    scheduleID, "Cancelled");
            if (result > 0) {
                response.put("scheduleID", scheduleID);
                response.put("status",     "Cancelled");
                response.put("reason",     reason);
                response.put("message",
                    "Schedule cancelled successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Schedule not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Schedule not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 18 — Delete schedule
    public Map<String, Object> deleteSchedule(int scheduleID) {
        Map<String, Object> response = new HashMap<>();
        try {
            String status =
                    repository.findStatusById(scheduleID);
            if (status.equals("Published")) {
                response.put("error",
                    "Cannot delete Published schedule.");
                response.put("statusCode", 400);
                return response;
            }
            int result = repository.delete(scheduleID);
            if (result > 0) {
                response.put("scheduleID", scheduleID);
                response.put("message",
                    "Schedule deleted successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Schedule not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Schedule not found");
            response.put("statusCode", 404);
        }
        return response;
    }
}