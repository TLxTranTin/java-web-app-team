package com.example.parking.pricing.dto;

import com.example.parking.pricing.domain.model.VehicleType;

import java.math.BigDecimal;

public record PricingRuleResponse(
        Long id,
        String name,
        VehicleType vehicleType,
        BigDecimal pricePerHour,
        BigDecimal maxDailyPrice,
        boolean active
) {}
