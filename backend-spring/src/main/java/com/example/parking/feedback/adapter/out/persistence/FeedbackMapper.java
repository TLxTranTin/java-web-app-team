package com.example.parking.feedback.adapter.out.persistence;

import com.example.parking.feedback.domain.model.Feedback;

public class FeedbackMapper {

    private FeedbackMapper() {
    }

    public static Feedback toDomain(FeedbackJpaEntity entity) {
        return new Feedback(
                entity.getId(),
                entity.getCustomerName(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getContent(),
                entity.getRating(),
                entity.getStatus(),
                entity.getResponseMessage(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static FeedbackJpaEntity toEntity(Feedback feedback) {
        FeedbackJpaEntity entity = new FeedbackJpaEntity();

        entity.setId(feedback.getId());
        entity.setCustomerName(feedback.getCustomerName());
        entity.setPhoneNumber(feedback.getPhoneNumber());
        entity.setEmail(feedback.getEmail());
        entity.setContent(feedback.getContent());
        entity.setRating(feedback.getRating());
        entity.setStatus(feedback.getStatus());
        entity.setResponseMessage(feedback.getResponseMessage());
        entity.setCreatedAt(feedback.getCreatedAt());
        entity.setUpdatedAt(feedback.getUpdatedAt());

        return entity;
    }
}