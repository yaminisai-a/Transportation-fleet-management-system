package com.tfms.backend.repository;

import com.tfms.backend.model.MaintenanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Long> {
    List<MaintenanceRecord> findByVehicleVehicleId(Long vehicleId);
}


