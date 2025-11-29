package com.tfms.backend.controller;

import com.tfms.backend.model.Trip;
import com.tfms.backend.service.TripService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<Trip> addTrip(@Valid @RequestBody Trip trip) {
        Trip created = tripService.createTrip(trip);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{tripId}")
    public Trip updateTrip(@PathVariable Long tripId, @Valid @RequestBody Trip trip) {
        return tripService.updateTrip(tripId, trip);
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Trip> viewTrips() {
        return tripService.getTrips();
    }
}


