package com.tfms.backend.dto;

import java.math.BigDecimal;

public class FuelUsageSummary {

    private BigDecimal totalFuelQuantity;
    private BigDecimal totalCost;

    public FuelUsageSummary(BigDecimal totalFuelQuantity, BigDecimal totalCost) {
        this.totalFuelQuantity = totalFuelQuantity;
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalFuelQuantity() {
        return totalFuelQuantity;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
}


