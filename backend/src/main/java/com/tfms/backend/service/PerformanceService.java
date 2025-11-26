package com.tfms.backend.service;

import com.tfms.backend.dto.PerformanceReportRequest;
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

    public PerformanceReport generateReport(PerformanceReportRequest request) {
        PerformanceReport report = new PerformanceReport();
        report.setReportType(request.getReportType());
        report.setData(request.getData());
        return performanceReportRepository.save(report);
    }

    @Transactional(readOnly = true)
    public List<PerformanceReport> getReports() {
        return performanceReportRepository.findAll();
    }
}


