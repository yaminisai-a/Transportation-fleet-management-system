package com.tfms.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class PerformanceReportRequest {

    @NotBlank
    private String reportType;

    @NotBlank
    private String data;

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
}


