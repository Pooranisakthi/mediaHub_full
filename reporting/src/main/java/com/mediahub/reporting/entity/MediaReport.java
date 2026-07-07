package com.mediahub.reporting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "media_report")
public class MediaReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportId")
    private Long reportId;
    @Column(name = "scope", nullable = false, length = 50)
    private String scope;
    @Column(name = "totalViews")
    private Long totalViews = 0L;
    @Column(name = "uniqueViewers")
    private Long uniqueViewers = 0L;
    @Column(name = "avgWatchTime", precision = 10, scale = 2)
    private BigDecimal avgWatchTime = BigDecimal.ZERO;
    @Column(name = "subscriptionRevenue", precision = 15, scale = 2)
    private BigDecimal subscriptionRevenue = BigDecimal.ZERO;
    @Column(name = "royaltyPaid", precision = 15, scale = 2)
    private BigDecimal royaltyPaid = BigDecimal.ZERO;
    @Column(name = "churnRate", precision = 5, scale = 2)
    private BigDecimal churnRate = BigDecimal.ZERO;
    @Column(name = "generatedDate", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime generatedDate;
}
