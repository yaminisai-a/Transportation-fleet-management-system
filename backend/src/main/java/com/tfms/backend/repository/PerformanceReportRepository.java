package com.tfms.backend.repository;

import com.tfms.backend.model.PerformanceReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceReportRepository extends JpaRepository<PerformanceReport, Long> {
}


