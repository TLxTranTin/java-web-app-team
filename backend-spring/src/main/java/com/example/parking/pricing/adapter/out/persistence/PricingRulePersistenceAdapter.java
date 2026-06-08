package com.example.parking.pricing.adapter.out.persistence;

import com.example.parking.pricing.application.port.out.IPricingRuleRepositoryPort;
import com.example.parking.pricing.domain.model.PricingRule;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PricingRulePersistenceAdapter implements IPricingRuleRepositoryPort {

    private final IPricingRuleJpaRepository jpaRepository;

    public PricingRulePersistenceAdapter(IPricingRuleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PricingRule save(PricingRule rule) {
        PricingRuleEntity entity = toEntity(rule);
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<PricingRule> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<PricingRule> findAll() {
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

    private PricingRule toDomain(PricingRuleEntity e) {
        return new PricingRule(e.getId(), e.getName(), e.getVehicleType(),
                e.getPricePerHour(), e.getMaxDailyPrice(), e.isActive());
    }

    private PricingRuleEntity toEntity(PricingRule r) {
        return new PricingRuleEntity(r.getId(), r.getName(), r.getVehicleType(),
                r.getPricePerHour(), r.getMaxDailyPrice(), r.isActive());
    }
}
