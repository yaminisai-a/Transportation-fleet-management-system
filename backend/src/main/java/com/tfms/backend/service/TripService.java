package com.tfms.backend.service;

import com.tfms.backend.model.Trip;
import com.tfms.backend.model.Vehicle;
import com.tfms.backend.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final VehicleService vehicleService;

    public TripService(TripRepository tripRepository, VehicleService vehicleService) {
        this.tripRepository = tripRepository;
        this.vehicleService = vehicleService;
    }

    public Trip createTrip(Trip trip) {
        Long vehicleId = trip.getVehicle() != null ? trip.getVehicle().getVehicleId() : null;
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle id is required");
        }
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        trip.setVehicle(vehicle);
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long tripId, Trip updatedTrip) {
        Long vehicleId = updatedTrip.getVehicle() != null ? updatedTrip.getVehicle().getVehicleId() : null;
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle id is required");
        }
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);

        Trip existing = tripRepository.findById(tripId)
            .orElseThrow(() -> new IllegalArgumentException("Trip not found: " + tripId));

        existing.setVehicle(vehicle);
        existing.setDriverId(updatedTrip.getDriverId());
        existing.setStartLocation(updatedTrip.getStartLocation());
        existing.setEndLocation(updatedTrip.getEndLocation());
        existing.setStartTime(updatedTrip.getStartTime());
        existing.setEndTime(updatedTrip.getEndTime());

        return tripRepository.save(existing);
    }

    public void deleteTrip(Long tripId) {
        Trip existing = tripRepository.findById(tripId)
            .orElseThrow(() -> new IllegalArgumentException("Trip not found: " + tripId));
        tripRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<Trip> getTrips() {
        return tripRepository.findAll();
    }
}


