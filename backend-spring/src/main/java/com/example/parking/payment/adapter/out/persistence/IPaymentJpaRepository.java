package com.example.parking.payment.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findByParkingSessionId(Long parkingSessionId);
}
