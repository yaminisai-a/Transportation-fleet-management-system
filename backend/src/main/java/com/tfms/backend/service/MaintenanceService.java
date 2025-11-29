package com.tfms.backend.service;

import com.tfms.backend.model.MaintenanceRecord;
import com.tfms.backend.model.Vehicle;
import com.tfms.backend.repository.MaintenanceRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaintenanceService {

    private final MaintenanceRecordRepository maintenanceRecordRepository;
    private final VehicleService vehicleService;

    public MaintenanceService(MaintenanceRecordRepository maintenanceRecordRepository,
                              VehicleService vehicleService) {
        this.maintenanceRecordRepository = maintenanceRecordRepository;
        this.vehicleService = vehicleService;
    }

    public MaintenanceRecord scheduleMaintenance(MaintenanceRecord maintenanceRecord) {
        Long vehicleId = maintenanceRecord.getVehicle() != null ? maintenanceRecord.getVehicle().getVehicleId() : null;
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle id is required");
        }
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        maintenanceRecord.setVehicle(vehicle);
        return maintenanceRecordRepository.save(maintenanceRecord);
    }

    public MaintenanceRecord updateMaintenance(Long maintenanceId, MaintenanceRecord updatedRecord) {
        Long vehicleId = updatedRecord.getVehicle() != null ? updatedRecord.getVehicle().getVehicleId() : null;
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle id is required");
        }
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);

        MaintenanceRecord existing = maintenanceRecordRepository.findById(maintenanceId)
            .orElseThrow(() -> new IllegalArgumentException("Maintenance record not found: " + maintenanceId));

        existing.setVehicle(vehicle);
        existing.setDescription(updatedRecord.getDescription());
        existing.setScheduledDate(updatedRecord.getScheduledDate());
        existing.setStatus(updatedRecord.getStatus());

        return maintenanceRecordRepository.save(existing);
    }

    public void deleteMaintenance(Long maintenanceId) {
        MaintenanceRecord existing = maintenanceRecordRepository.findById(maintenanceId)
            .orElseThrow(() -> new IllegalArgumentException("Maintenance record not found: " + maintenanceId));
        maintenanceRecordRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<MaintenanceRecord> getMaintenanceRecords() {
        return maintenanceRecordRepository.findAll();
    }
}


