package com.example.parking.pricing.application.port.out;

import com.example.parking.pricing.domain.model.PricingRule;

import java.util.List;
import java.util.Optional;

public interface IPricingRuleRepositoryPort {
    PricingRule save(PricingRule rule);
    Optional<PricingRule> findById(Long id);
    List<PricingRule> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
