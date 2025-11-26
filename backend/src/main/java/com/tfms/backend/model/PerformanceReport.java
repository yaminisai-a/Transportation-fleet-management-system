package com.tfms.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class PerformanceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceId;

    @NotBlank(message = "Report type is required")
    private String reportType;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String data;

    private LocalDateTime generatedOn;

    @PrePersist
    public void prePersist() {
        generatedOn = LocalDateTime.now();
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(LocalDateTime generatedOn) {
        this.generatedOn = generatedOn;
    }
}


