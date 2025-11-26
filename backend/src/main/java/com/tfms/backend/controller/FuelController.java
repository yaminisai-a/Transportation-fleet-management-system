package com.tfms.backend.controller;

import com.tfms.backend.dto.FuelRecordRequest;
import com.tfms.backend.dto.FuelUsageSummary;
import com.tfms.backend.model.FuelRecord;
import com.tfms.backend.service.FuelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel")
public class FuelController {

    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping
    public ResponseEntity<FuelRecord> addFuelRecord(@Valid @RequestBody FuelRecordRequest request) {
        FuelRecord created = fuelService.addFuelRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<FuelRecord> viewFuelRecords() {
        return fuelService.getFuelRecords();
    }

    @GetMapping("/usage")
    public FuelUsageSummary viewFuelUsage() {
        return fuelService.getUsageSummary();
    }
}


