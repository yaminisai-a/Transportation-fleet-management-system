package com.tfms.backend.controller;

import com.tfms.backend.model.Vehicle;
import com.tfms.backend.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle created = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{vehicleId}")
    public Vehicle updateVehicle(@PathVariable Long vehicleId, @Valid @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicleId, vehicle);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Vehicle> viewVehicles() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/{vehicleId}")
    public Vehicle viewVehicle(@PathVariable Long vehicleId) {
        return vehicleService.getVehicle(vehicleId);
    }
}


