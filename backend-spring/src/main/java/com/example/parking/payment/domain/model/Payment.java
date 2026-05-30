package com.example.parking.payment.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private Long parkingSessionId;
    private BigDecimal amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private LocalDateTime paidAt;
    private String note;

    public Payment() {}

    public Payment(Long id, Long parkingSessionId, BigDecimal amount,
                   PaymentMethod method, PaymentStatus status,
                   LocalDateTime paidAt, String note) {
        this.id = id;
        this.parkingSessionId = parkingSessionId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.paidAt = paidAt;
        this.note = note;
    }

    public Long getId() { return id; }
    public Long getParkingSessionId() { return parkingSessionId; }
    public BigDecimal getAmount() { return amount; }
    public PaymentMethod getMethod() { return method; }
    public PaymentStatus getStatus() { return status; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public String getNote() { return note; }
}
