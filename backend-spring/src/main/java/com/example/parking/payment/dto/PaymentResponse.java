package com.example.parking.payment.dto;

import com.example.parking.payment.domain.model.PaymentMethod;
import com.example.parking.payment.domain.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long parkingSessionId,
        BigDecimal amount,
        PaymentMethod method,
        PaymentStatus status,
        LocalDateTime paidAt,
        String note
) {}
