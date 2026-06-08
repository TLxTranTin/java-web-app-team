package com.example.parking.pricing.adapter.out.persistence;

import com.example.parking.pricing.domain.model.VehicleType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pricing_rules")
public class PricingRuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleType vehicleType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerHour;

    @Column(precision = 10, scale = 2)
    private BigDecimal maxDailyPrice;

    @Column(nullable = false)
    private boolean active;

    public PricingRuleEntity() {}

    public PricingRuleEntity(Long id, String name, VehicleType vehicleType,
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
