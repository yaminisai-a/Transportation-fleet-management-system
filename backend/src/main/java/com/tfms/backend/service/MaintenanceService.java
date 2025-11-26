package com.tfms.backend.service;

import com.tfms.backend.dto.MaintenanceRequest;
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

    public MaintenanceRecord scheduleMaintenance(MaintenanceRequest request) {
        Vehicle vehicle = vehicleService.getVehicle(request.getVehicleId());
        MaintenanceRecord record = new MaintenanceRecord();
        record.setVehicle(vehicle);
        record.setDescription(request.getDescription());
        record.setScheduledDate(request.getScheduledDate());
        record.setStatus(request.getStatus());
        return maintenanceRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public List<MaintenanceRecord> getMaintenanceRecords() {
        return maintenanceRecordRepository.findAll();
    }
}


