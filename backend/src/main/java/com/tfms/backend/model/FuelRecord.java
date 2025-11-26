package com.tfms.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class FuelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vehicle vehicle;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Fuel quantity is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fuel quantity must be positive")
    private BigDecimal fuelQuantity;

    @NotNull(message = "Fuel cost is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Cost must be positive")
    private BigDecimal cost;

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(BigDecimal fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}


