package com.example.parking.pricing.adapter.in.web;

import com.example.parking.pricing.application.port.in.IPricingRuleUseCase;
import com.example.parking.pricing.dto.PricingRuleRequest;
import com.example.parking.pricing.dto.PricingRuleResponse;
import com.example.parking.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing-rules")
public class PricingRuleController {

    private final IPricingRuleUseCase pricingRuleUseCase;

    public PricingRuleController(IPricingRuleUseCase pricingRuleUseCase) {
        this.pricingRuleUseCase = pricingRuleUseCase;
    }

    @PostMapping
    public ApiResponse<PricingRuleResponse> create(@Valid @RequestBody PricingRuleRequest request) {
        PricingRuleResponse response = pricingRuleUseCase.createPricingRule(request);
        return ApiResponse.success("Tạo quy tắc giá thành công", response);
    }

    @GetMapping("/{id}")
    public ApiResponse<PricingRuleResponse> getById(@PathVariable Long id) {
        PricingRuleResponse response = pricingRuleUseCase.getPricingRule(id);
        return ApiResponse.success("Lấy quy tắc giá thành công", response);
    }

    @GetMapping
    public ApiResponse<List<PricingRuleResponse>> getAll() {
        List<PricingRuleResponse> list = pricingRuleUseCase.getAllPricingRules();
        return ApiResponse.success("Lấy danh sách quy tắc giá thành công", list);
    }

    @PutMapping("/{id}")
    public ApiResponse<PricingRuleResponse> update(@PathVariable Long id,
                                                    @Valid @RequestBody PricingRuleRequest request) {
        PricingRuleResponse response = pricingRuleUseCase.updatePricingRule(id, request);
        return ApiResponse.success("Cập nhật quy tắc giá thành công", response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        pricingRuleUseCase.deletePricingRule(id);
        return ApiResponse.success("Xóa quy tắc giá thành công", null);
    }
}
