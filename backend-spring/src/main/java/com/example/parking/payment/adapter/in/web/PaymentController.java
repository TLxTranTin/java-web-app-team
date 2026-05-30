package com.example.parking.payment.adapter.in.web;

import com.example.parking.payment.application.port.in.IPaymentUseCase;
import com.example.parking.payment.dto.PaymentRequest;
import com.example.parking.payment.dto.PaymentResponse;
import com.example.parking.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final IPaymentUseCase paymentUseCase;

    public PaymentController(IPaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    @PostMapping
    public ApiResponse<PaymentResponse> create(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentUseCase.createPayment(request);
        return ApiResponse.success("Tạo thanh toán thành công", response);
    }

    @GetMapping("/{id}")
    public ApiResponse<PaymentResponse> getById(@PathVariable Long id) {
        PaymentResponse response = paymentUseCase.getPayment(id);
        return ApiResponse.success("Lấy thông tin thanh toán thành công", response);
    }

    @GetMapping
    public ApiResponse<List<PaymentResponse>> getAll() {
        List<PaymentResponse> list = paymentUseCase.getAllPayments();
        return ApiResponse.success("Lấy danh sách thanh toán thành công", list);
    }

    @PutMapping("/{id}")
    public ApiResponse<PaymentResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentUseCase.updatePayment(id, request);
        return ApiResponse.success("Cập nhật thanh toán thành công", response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        paymentUseCase.deletePayment(id);
        return ApiResponse.success("Xóa thanh toán thành công", null);
    }
}
