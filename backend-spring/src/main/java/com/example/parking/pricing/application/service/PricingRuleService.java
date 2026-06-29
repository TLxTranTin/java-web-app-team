package com.example.parking.pricing.application.service;

import com.example.parking.pricing.application.port.in.IPricingRuleUseCase;
import com.example.parking.pricing.application.port.out.IPricingRuleRepositoryPort;
import com.example.parking.pricing.domain.model.PricingRule;
import com.example.parking.pricing.dto.PricingRuleRequest;
import com.example.parking.pricing.dto.PricingRuleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingRuleService implements IPricingRuleUseCase {

    private final IPricingRuleRepositoryPort repositoryPort;

    public PricingRuleService(IPricingRuleRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public PricingRuleResponse createPricingRule(PricingRuleRequest request) {
        PricingRule rule = new PricingRule(
                null,
                request.name(),
                request.vehicleType(),
                request.pricePerHour(),
                request.maxDailyPrice(),
                request.active()
        );
        PricingRule saved = repositoryPort.save(rule);
        return toResponse(saved);
    }

    @Override
    public PricingRuleResponse getPricingRule(Long id) {
        PricingRule rule = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quy tắc giá với id: " + id));
        return toResponse(rule);
    }

    @Override
    public List<PricingRuleResponse> getAllPricingRules() {
        return repositoryPort.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public PricingRuleResponse updatePricingRule(Long id, PricingRuleRequest request) {
        if (!repositoryPort.existsById(id)) {
            throw new RuntimeException("Không tìm thấy quy tắc giá với id: " + id);
        }
        PricingRule rule = new PricingRule(
                id,
                request.name(),
                request.vehicleType(),
                request.pricePerHour(),
                request.maxDailyPrice(),
                request.active()
        );
        PricingRule updated = repositoryPort.save(rule);
        return toResponse(updated);
    }

    @Override
    public void deletePricingRule(Long id) {
        if (!repositoryPort.existsById(id)) {
            throw new RuntimeException("Không tìm thấy quy tắc giá với id: " + id);
        }
        repositoryPort.deleteById(id);
    }

    private PricingRuleResponse toResponse(PricingRule rule) {
        return new PricingRuleResponse(
                rule.getId(),
                rule.getName(),
                rule.getVehicleType(),
                rule.getPricePerHour(),
                rule.getMaxDailyPrice(),
                rule.isActive()
        );
    }
}
