package com.example.parking.incident.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentJpaRepository extends JpaRepository<IncidentJpaEntity, Long> {
}