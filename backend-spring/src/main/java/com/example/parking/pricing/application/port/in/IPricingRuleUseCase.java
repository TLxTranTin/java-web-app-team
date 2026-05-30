package com.example.parking.pricing.application.port.in;

import com.example.parking.pricing.dto.PricingRuleRequest;
import com.example.parking.pricing.dto.PricingRuleResponse;

import java.util.List;

public interface IPricingRuleUseCase {
    PricingRuleResponse createPricingRule(PricingRuleRequest request);
    PricingRuleResponse getPricingRule(Long id);
    List<PricingRuleResponse> getAllPricingRules();
    PricingRuleResponse updatePricingRule(Long id, PricingRuleRequest request);
    void deletePricingRule(Long id);
}
