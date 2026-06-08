package com.example.parking.pricing.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPricingRuleJpaRepository extends JpaRepository<PricingRuleEntity, Long> {}
