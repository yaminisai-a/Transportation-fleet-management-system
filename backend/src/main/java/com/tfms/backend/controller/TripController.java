package com.tfms.backend.controller;

import com.tfms.backend.dto.TripRequest;
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
    public ResponseEntity<Trip> addTrip(@Valid @RequestBody TripRequest request) {
        Trip created = tripService.createTrip(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{tripId}")
    public Trip updateTrip(@PathVariable Long tripId, @Valid @RequestBody TripRequest request) {
        return tripService.updateTrip(tripId, request);
    }

    @GetMapping
    public List<Trip> viewTrips() {
        return tripService.getTrips();
    }
}


