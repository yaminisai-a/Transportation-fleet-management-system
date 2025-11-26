package com.tfms.backend.service;

import com.tfms.backend.dto.TripRequest;
import com.tfms.backend.exception.ResourceNotFoundException;
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

    public Trip createTrip(TripRequest request) {
        Trip trip = mapToTrip(request, new Trip());
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long tripId, TripRequest request) {
        Trip existing = tripRepository.findById(tripId)
            .orElseThrow(() -> new ResourceNotFoundException("Trip not found: " + tripId));
        Trip updated = mapToTrip(request, existing);
        updated.setTripId(existing.getTripId());
        return tripRepository.save(updated);
    }

    @Transactional(readOnly = true)
    public List<Trip> getTrips() {
        return tripRepository.findAll();
    }

    private Trip mapToTrip(TripRequest request, Trip trip) {
        Vehicle vehicle = vehicleService.getVehicle(request.getVehicleId());
        trip.setVehicle(vehicle);
        trip.setDriverId(request.getDriverId());
        trip.setStartLocation(request.getStartLocation());
        trip.setEndLocation(request.getEndLocation());
        trip.setStartTime(request.getStartTime());
        trip.setEndTime(request.getEndTime());
        return trip;
    }
}


