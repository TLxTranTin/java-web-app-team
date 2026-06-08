package com.example.parking.payment.application.service;

import com.example.parking.payment.application.port.in.IPaymentUseCase;
import com.example.parking.payment.application.port.out.IPaymentRepositoryPort;
import com.example.parking.payment.domain.model.Payment;
import com.example.parking.payment.domain.model.PaymentStatus;
import com.example.parking.payment.dto.PaymentRequest;
import com.example.parking.payment.dto.PaymentResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService implements IPaymentUseCase {

    private final IPaymentRepositoryPort repositoryPort;

    public PaymentService(IPaymentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        PaymentStatus status = request.status() != null ? request.status() : PaymentStatus.PENDING;
        Payment payment = new Payment(
                null,
                request.parkingSessionId(),
                request.amount(),
                request.method(),
                status,
                LocalDateTime.now(),
                request.note()
        );
        Payment saved = repositoryPort.save(payment);
        return toResponse(saved);
    }

    @Override
    public PaymentResponse getPayment(Long id) {
        Payment payment = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thanh toán với id: " + id));
        return toResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return repositoryPort.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public PaymentResponse updatePayment(Long id, PaymentRequest request) {
        Payment existing = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thanh toán với id: " + id));
        PaymentStatus status = request.status() != null ? request.status() : existing.getStatus();
        Payment updated = new Payment(
                id,
                request.parkingSessionId(),
                request.amount(),
                request.method(),
                status,
                existing.getPaidAt(),
                request.note()
        );
        return toResponse(repositoryPort.save(updated));
    }

    @Override
    public void deletePayment(Long id) {
        if (!repositoryPort.existsById(id)) {
            throw new RuntimeException("Không tìm thấy thanh toán với id: " + id);
        }
        repositoryPort.deleteById(id);
    }

    private PaymentResponse toResponse(Payment p) {
        return new PaymentResponse(
                p.getId(),
                p.getParkingSessionId(),
                p.getAmount(),
                p.getMethod(),
                p.getStatus(),
                p.getPaidAt(),
                p.getNote()
        );
    }
}
