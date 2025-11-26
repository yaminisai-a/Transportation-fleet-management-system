package com.tfms.backend.service;

import com.tfms.backend.exception.ResourceNotFoundException;
import com.tfms.backend.model.Vehicle;
import com.tfms.backend.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        vehicleRepository.findByRegistrationNumberIgnoreCase(vehicle.getRegistrationNumber())
            .ifPresent(existing -> { throw new IllegalArgumentException("Registration number already exists"); });
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle existing = getVehicle(id);
        vehicleRepository.findByRegistrationNumberIgnoreCase(updatedVehicle.getRegistrationNumber())
            .filter(vehicle -> !vehicle.getVehicleId().equals(id))
            .ifPresent(conflict -> { throw new IllegalArgumentException("Registration number already exists"); });
        existing.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
        existing.setCapacity(updatedVehicle.getCapacity());
        existing.setStatus(updatedVehicle.getStatus());
        existing.setLastServicedDate(updatedVehicle.getLastServicedDate());
        return vehicleRepository.save(existing);
    }

    public void deleteVehicle(Long id) {
        Vehicle existing = getVehicle(id);
        vehicleRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found: " + id));
    }
}

