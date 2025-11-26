package com.tfms.backend.repository;

import com.tfms.backend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByRegistrationNumberIgnoreCase(String registrationNumber);
}


