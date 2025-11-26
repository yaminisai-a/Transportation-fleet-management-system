package com.tfms.backend.controller;

import com.tfms.backend.dto.MaintenanceRequest;
import com.tfms.backend.model.MaintenanceRecord;
import com.tfms.backend.service.MaintenanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public ResponseEntity<MaintenanceRecord> scheduleMaintenance(@Valid @RequestBody MaintenanceRequest request) {
        MaintenanceRecord record = maintenanceService.scheduleMaintenance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(record);
    }

    @GetMapping
    public List<MaintenanceRecord> viewMaintenanceRecords() {
        return maintenanceService.getMaintenanceRecords();
    }
}


