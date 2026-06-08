package com.example.parking.payment.dto;

import com.example.parking.payment.domain.model.PaymentMethod;
import com.example.parking.payment.domain.model.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequest(
        @NotNull(message = "ID phiên gửi xe không được để trống")
        Long parkingSessionId,

        @NotNull(message = "Số tiền không được để trống")
        @Positive(message = "Số tiền phải lớn hơn 0")
        BigDecimal amount,

        @NotNull(message = "Phương thức thanh toán không được để trống")
        PaymentMethod method,

        PaymentStatus status,

        String note
) {}
