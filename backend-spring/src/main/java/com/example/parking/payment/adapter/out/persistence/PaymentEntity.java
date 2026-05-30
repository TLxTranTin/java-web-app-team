package com.example.parking.payment.adapter.out.persistence;

import com.example.parking.payment.domain.model.PaymentMethod;
import com.example.parking.payment.domain.model.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long parkingSessionId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column
    private LocalDateTime paidAt;

    @Column(length = 255)
    private String note;

    public PaymentEntity() {}

    public PaymentEntity(Long id, Long parkingSessionId, BigDecimal amount,
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
