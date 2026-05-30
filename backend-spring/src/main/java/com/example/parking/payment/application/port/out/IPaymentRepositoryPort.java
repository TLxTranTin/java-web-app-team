package com.example.parking.payment.application.port.out;

import com.example.parking.payment.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface IPaymentRepositoryPort {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    List<Payment> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
