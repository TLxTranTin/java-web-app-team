package com.example.parking.pricing.dto;

import com.example.parking.pricing.domain.model.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PricingRuleRequest(
        @NotBlank(message = "Tên quy tắc không được để trống")
        String name,

        @NotNull(message = "Loại phương tiện không được để trống")
        VehicleType vehicleType,

        @NotNull(message = "Giá theo giờ không được để trống")
        @Positive(message = "Giá theo giờ phải lớn hơn 0")
        BigDecimal pricePerHour,

        @Positive(message = "Giá tối đa hàng ngày phải lớn hơn 0")
        BigDecimal maxDailyPrice,

        boolean active
) {}
