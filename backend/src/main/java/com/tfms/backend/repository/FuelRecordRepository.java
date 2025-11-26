package com.tfms.backend.repository;

import com.tfms.backend.model.FuelRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelRecordRepository extends JpaRepository<FuelRecord, Long> {
    List<FuelRecord> findByVehicleVehicleId(Long vehicleId);
}


