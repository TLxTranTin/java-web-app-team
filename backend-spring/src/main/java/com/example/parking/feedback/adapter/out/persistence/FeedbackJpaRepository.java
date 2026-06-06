package com.example.parking.feedback.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackJpaRepository extends JpaRepository<FeedbackJpaEntity, Long> {
}