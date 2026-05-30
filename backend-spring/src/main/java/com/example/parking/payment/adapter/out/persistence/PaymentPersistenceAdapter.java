package com.example.parking.payment.adapter.out.persistence;

import com.example.parking.payment.application.port.out.IPaymentRepositoryPort;
import com.example.parking.payment.domain.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentPersistenceAdapter implements IPaymentRepositoryPort {

    private final IPaymentJpaRepository jpaRepository;

    public PaymentPersistenceAdapter(IPaymentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return toDomain(jpaRepository.save(toEntity(payment)));
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Payment> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    private Payment toDomain(PaymentEntity e) {
        return new Payment(e.getId(), e.getParkingSessionId(), e.getAmount(),
                e.getMethod(), e.getStatus(), e.getPaidAt(), e.getNote());
    }

    private PaymentEntity toEntity(Payment p) {
        return new PaymentEntity(p.getId(), p.getParkingSessionId(), p.getAmount(),
                p.getMethod(), p.getStatus(), p.getPaidAt(), p.getNote());
    }
}
