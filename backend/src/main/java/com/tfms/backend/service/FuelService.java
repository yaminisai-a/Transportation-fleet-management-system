package com.tfms.backend.service;

import com.tfms.backend.dto.FuelRecordRequest;
import com.tfms.backend.dto.FuelUsageSummary;
import com.tfms.backend.model.FuelRecord;
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

    public FuelRecord addFuelRecord(FuelRecordRequest request) {
        Vehicle vehicle = vehicleService.getVehicle(request.getVehicleId());
        FuelRecord record = new FuelRecord();
        record.setVehicle(vehicle);
        record.setDate(request.getDate());
        record.setFuelQuantity(request.getFuelQuantity());
        record.setCost(request.getCost());
        return fuelRecordRepository.save(record);
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


