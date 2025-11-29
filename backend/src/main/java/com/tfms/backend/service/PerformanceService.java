package com.tfms.backend.service;

import com.tfms.backend.model.PerformanceReport;
import com.tfms.backend.repository.PerformanceReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PerformanceService {

    private final PerformanceReportRepository performanceReportRepository;

    public PerformanceService(PerformanceReportRepository performanceReportRepository) {
        this.performanceReportRepository = performanceReportRepository;
    }

    public PerformanceReport generateReport(PerformanceReport performanceReport) {
        return performanceReportRepository.save(performanceReport);
    }

    public PerformanceReport updateReport(Long performanceId, PerformanceReport updatedReport) {
        PerformanceReport existing = performanceReportRepository.findById(performanceId)
            .orElseThrow(() -> new IllegalArgumentException("Performance report not found: " + performanceId));

        existing.setReportType(updatedReport.getReportType());
        existing.setData(updatedReport.getData());
        // generatedOn is managed by @PrePersist; we keep the original timestamp

        return performanceReportRepository.save(existing);
    }

    public void deleteReport(Long performanceId) {
        PerformanceReport existing = performanceReportRepository.findById(performanceId)
            .orElseThrow(() -> new IllegalArgumentException("Performance report not found: " + performanceId));
        performanceReportRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<PerformanceReport> getReports() {
        return performanceReportRepository.findAll();
    }
}


