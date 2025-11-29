package com.tfms.backend.service;

import com.tfms.backend.model.FuelRecord;
import com.tfms.backend.model.FuelUsageSummary;
import com.tfms.backend.model.Vehicle;
import com.tfms.backend.repository.FuelRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class FuelService {

    private final FuelRecordRepository fuelRecordRepository;
    private final VehicleService vehicleService;

    public FuelService(FuelRecordRepository fuelRecordRepository, VehicleService vehicleService) {
        this.fuelRecordRepository = fuelRecordRepository;
        this.vehicleService = vehicleService;
    }

    public FuelRecord addFuelRecord(FuelRecord fuelRecord) {
        Long vehicleId = fuelRecord.getVehicle() != null ? fuelRecord.getVehicle().getVehicleId() : null;
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle id is required");
        }
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        fuelRecord.setVehicle(vehicle);
        return fuelRecordRepository.save(fuelRecord);
    }

    public FuelRecord updateFuelRecord(Long fuelId, FuelRecord updatedRecord) {
        Long vehicleId = updatedRecord.getVehicle() != null ? updatedRecord.getVehicle().getVehicleId() : null;
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle id is required");
        }
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);

        FuelRecord existing = fuelRecordRepository.findById(fuelId)
            .orElseThrow(() -> new IllegalArgumentException("Fuel record not found: " + fuelId));

        existing.setVehicle(vehicle);
        existing.setDate(updatedRecord.getDate());
        existing.setFuelQuantity(updatedRecord.getFuelQuantity());
        existing.setCost(updatedRecord.getCost());

        return fuelRecordRepository.save(existing);
    }

    public void deleteFuelRecord(Long fuelId) {
        FuelRecord existing = fuelRecordRepository.findById(fuelId)
            .orElseThrow(() -> new IllegalArgumentException("Fuel record not found: " + fuelId));
        fuelRecordRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<FuelRecord> getFuelRecords() {
        return fuelRecordRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FuelUsageSummary getUsageSummary() {
        List<FuelRecord> records = fuelRecordRepository.findAll();
        BigDecimal totalFuel = records.stream()
            .map(FuelRecord::getFuelQuantity)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCost = records.stream()
            .map(FuelRecord::getCost)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new FuelUsageSummary(totalFuel, totalCost);
    }
}


