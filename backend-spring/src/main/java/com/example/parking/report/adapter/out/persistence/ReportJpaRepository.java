package com.example.parking.report.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportJpaRepository extends JpaRepository<ReportJpaEntity, Long> {
}