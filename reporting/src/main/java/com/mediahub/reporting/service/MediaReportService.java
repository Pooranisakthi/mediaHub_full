package com.mediahub.reporting.service;

import com.mediahub.reporting.entity.MediaReport;
import com.mediahub.reporting.repository.MediaReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MediaReportService {
    @Autowired
    private MediaReportRepository mediaReportRepository;

    public MediaReport createReport(MediaReport mediaReport) {
        return mediaReportRepository.save(mediaReport);
    }

    public List<MediaReport> fetchReports() {
        List<MediaReport> list = mediaReportRepository.findAll();
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No reports found");
        return list;
    }

    public MediaReport fetchReport(Long reportId) {
        return mediaReportRepository.findById(reportId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));
    }

    public List<MediaReport> fetchByScope(String scope) {
        List<MediaReport> list = mediaReportRepository.findByScope(scope);
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No reports found for scope: " + scope);
        return list;
    }

    public MediaReport updateReport(Long reportId, MediaReport updatedReport) {
        MediaReport existing = fetchReport(reportId);
        if (updatedReport.getTotalViews() != null) existing.setTotalViews(updatedReport.getTotalViews());
        if (updatedReport.getUniqueViewers() != null) existing.setUniqueViewers(updatedReport.getUniqueViewers());
        if (updatedReport.getAvgWatchTime() != null) existing.setAvgWatchTime(updatedReport.getAvgWatchTime());
        if (updatedReport.getSubscriptionRevenue() != null) existing.setSubscriptionRevenue(updatedReport.getSubscriptionRevenue());
        if (updatedReport.getRoyaltyPaid() != null) existing.setRoyaltyPaid(updatedReport.getRoyaltyPaid());
        if (updatedReport.getChurnRate() != null) existing.setChurnRate(updatedReport.getChurnRate());
        return mediaReportRepository.save(existing);
    }

    public void deleteReport(Long reportId) {
        if (!mediaReportRepository.existsById(reportId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        mediaReportRepository.deleteById(reportId);
    }
}
