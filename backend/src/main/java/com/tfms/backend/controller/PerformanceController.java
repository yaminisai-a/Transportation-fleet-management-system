package com.tfms.backend.controller;

import com.tfms.backend.dto.PerformanceReportRequest;
import com.tfms.backend.model.PerformanceReport;
import com.tfms.backend.service.PerformanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @PostMapping
    public ResponseEntity<PerformanceReport> generateReport(@Valid @RequestBody PerformanceReportRequest request) {
        PerformanceReport report = performanceService.generateReport(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }

    @GetMapping
    public List<PerformanceReport> viewDashboard() {
        return performanceService.getReports();
    }
}


