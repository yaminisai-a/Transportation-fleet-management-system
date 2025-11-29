package com.tfms.backend.controller;

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
    public ResponseEntity<MaintenanceRecord> scheduleMaintenance(@Valid @RequestBody MaintenanceRecord maintenanceRecord) {
        MaintenanceRecord record = maintenanceService.scheduleMaintenance(maintenanceRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(record);
    }

    @PutMapping("/{maintenanceId}")
    public MaintenanceRecord updateMaintenance(@PathVariable Long maintenanceId,
                                               @Valid @RequestBody MaintenanceRecord maintenanceRecord) {
        return maintenanceService.updateMaintenance(maintenanceId, maintenanceRecord);
    }

    @DeleteMapping("/{maintenanceId}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long maintenanceId) {
        maintenanceService.deleteMaintenance(maintenanceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<MaintenanceRecord> viewMaintenanceRecords() {
        return maintenanceService.getMaintenanceRecords();
    }
}


