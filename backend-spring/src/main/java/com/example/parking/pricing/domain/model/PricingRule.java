package com.example.parking.pricing.domain.model;

import java.math.BigDecimal;

public class PricingRule {
    private Long id;
    private String name;
    private VehicleType vehicleType;
    private BigDecimal pricePerHour;
    private BigDecimal maxDailyPrice;
    private boolean active;

    public PricingRule() {}

    public PricingRule(Long id, String name, VehicleType vehicleType,
                       BigDecimal pricePerHour, BigDecimal maxDailyPrice, boolean active) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.pricePerHour = pricePerHour;
        this.maxDailyPrice = maxDailyPrice;
        this.active = active;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public VehicleType getVehicleType() { return vehicleType; }
    public BigDecimal getPricePerHour() { return pricePerHour; }
    public BigDecimal getMaxDailyPrice() { return maxDailyPrice; }
    public boolean isActive() { return active; }
}
