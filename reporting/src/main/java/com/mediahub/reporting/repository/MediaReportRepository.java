package com.mediahub.reporting.repository;

import com.mediahub.reporting.entity.MediaReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaReportRepository extends JpaRepository<MediaReport, Long> {
    List<MediaReport> findByScope(String scope);
}
