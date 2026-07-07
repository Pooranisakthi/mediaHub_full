package com.mediahub.reporting.controller;

import com.mediahub.reporting.entity.MediaReport;
import com.mediahub.reporting.service.MediaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mediaHub/analytics/reports")
public class MediaReportController {
    @Autowired
    private MediaReportService mediaReportService;

    @PostMapping("/createReport")
    public ResponseEntity<?> createReport(@RequestBody MediaReport mediaReport) {
        mediaReportService.createReport(mediaReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Report created successfully"));
    }

    @GetMapping("/fetchReports")
    public ResponseEntity<List<MediaReport>> fetchReports() {
        return ResponseEntity.ok(mediaReportService.fetchReports());
    }

    @GetMapping("/fetchReport/{reportId}")
    public ResponseEntity<MediaReport> fetchReport(@PathVariable Long reportId) {
        return ResponseEntity.ok(mediaReportService.fetchReport(reportId));
    }

    @GetMapping("/fetchByScope/{scope}")
    public ResponseEntity<List<MediaReport>> fetchByScope(@PathVariable String scope) {
        return ResponseEntity.ok(mediaReportService.fetchByScope(scope));
    }

    @PutMapping("/updateReport/{reportId}")
    public ResponseEntity<?> updateReport(@PathVariable Long reportId, @RequestBody MediaReport mediaReport) {
        mediaReportService.updateReport(reportId, mediaReport);
        return ResponseEntity.ok(Map.of("message", "Report updated successfully"));
    }

    @DeleteMapping("/deleteReport/{reportId}")
    public ResponseEntity<?> deleteReport(@PathVariable Long reportId) {
        mediaReportService.deleteReport(reportId);
        return ResponseEntity.ok(Map.of("message", "Report deleted successfully"));
    }
}
