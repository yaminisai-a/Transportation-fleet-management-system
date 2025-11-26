package com.tfms.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FuelRecordRequest {

    @NotNull
    private Long vehicleId;

    @NotNull
    private LocalDate date;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal fuelQuantity;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal cost;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
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


