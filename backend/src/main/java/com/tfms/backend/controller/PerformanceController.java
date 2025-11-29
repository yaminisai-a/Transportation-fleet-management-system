package com.tfms.backend.controller;

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
    public ResponseEntity<PerformanceReport> generateReport(@Valid @RequestBody PerformanceReport performanceReport) {
        PerformanceReport report = performanceService.generateReport(performanceReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }

    @PutMapping("/{performanceId}")
    public PerformanceReport updateReport(@PathVariable Long performanceId,
                                          @Valid @RequestBody PerformanceReport performanceReport) {
        return performanceService.updateReport(performanceId, performanceReport);
    }

    @DeleteMapping("/{performanceId}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long performanceId) {
        performanceService.deleteReport(performanceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<PerformanceReport> viewDashboard() {
        return performanceService.getReports();
    }
}


