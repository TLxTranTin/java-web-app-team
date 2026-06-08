package com.example.parking.payment.application.port.in;

import com.example.parking.payment.dto.PaymentRequest;
import com.example.parking.payment.dto.PaymentResponse;

import java.util.List;

public interface IPaymentUseCase {
    PaymentResponse createPayment(PaymentRequest request);
    PaymentResponse getPayment(Long id);
    List<PaymentResponse> getAllPayments();
    PaymentResponse updatePayment(Long id, PaymentRequest request);
    void deletePayment(Long id);
}
