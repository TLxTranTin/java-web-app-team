package com.example.parking.payment.adapter.in.web;

import com.example.parking.payment.application.port.in.IPaymentUseCase;
import com.example.parking.payment.application.service.MomoService;
import com.example.parking.payment.dto.PaymentRequest;
import com.example.parking.payment.dto.PaymentResponse;
import com.example.parking.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final IPaymentUseCase paymentUseCase;
    private final MomoService momoService;

    public PaymentController(IPaymentUseCase paymentUseCase, MomoService momoService) {
        this.paymentUseCase = paymentUseCase;
        this.momoService = momoService;
    }

    // ===== CRUD cũ =====

    @PostMapping
    public ApiResponse<PaymentResponse> create(@Valid @RequestBody PaymentRequest request) {
        return ApiResponse.success("Tạo thanh toán thành công", paymentUseCase.createPayment(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<PaymentResponse> getById(@PathVariable Long id) {
        return ApiResponse.success("Lấy thông tin thanh toán thành công", paymentUseCase.getPayment(id));
    }

    @GetMapping
    public ApiResponse<List<PaymentResponse>> getAll() {
        return ApiResponse.success("Lấy danh sách thanh toán thành công", paymentUseCase.getAllPayments());
    }

    @PutMapping("/{id}")
    public ApiResponse<PaymentResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody PaymentRequest request) {
        return ApiResponse.success("Cập nhật thanh toán thành công", paymentUseCase.updatePayment(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        paymentUseCase.deletePayment(id);
        return ApiResponse.success("Xóa thanh toán thành công", null);
    }

    // ===== MoMo =====

    @PostMapping("/momo/create")
    public ApiResponse<String> createMomoPayment(
            @RequestParam Long orderId,
            @RequestParam Long amount) throws Exception {
        String payUrl = momoService.createPaymentUrl(orderId, amount);
        return ApiResponse.success("Tạo link MoMo thành công", payUrl);
    }

    @GetMapping("/momo/return")
    public ApiResponse<String> momoReturn(@RequestParam Map<String, String> params) {
        String resultCode = params.get("resultCode");
        if ("0".equals(resultCode)) {
            return ApiResponse.success("Thanh toán thành công!", null);
        }
        return ApiResponse.success("Thanh toán thất bại!", null);
    }

    @PostMapping("/momo/notify")
    public ResponseEntity<String> momoNotify(
            @RequestBody Map<String, String> params) throws Exception {
        if (momoService.verifyCallback(params)) {
            String resultCode = params.get("resultCode");
            if ("0".equals(resultCode)) {
                System.out.println("Thanh toán thành công, orderId: " + params.get("orderId"));
            }
        }
        return ResponseEntity.ok("OK");
    }
}