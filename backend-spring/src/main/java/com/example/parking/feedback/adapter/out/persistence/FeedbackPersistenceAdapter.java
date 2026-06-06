package com.example.parking.feedback.adapter.out.persistence;

import com.example.parking.feedback.application.port.out.FeedbackRepositoryPort;
import com.example.parking.feedback.domain.model.Feedback;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FeedbackPersistenceAdapter implements FeedbackRepositoryPort {

    private final FeedbackJpaRepository feedbackJpaRepository;

    public FeedbackPersistenceAdapter(FeedbackJpaRepository feedbackJpaRepository) {
        this.feedbackJpaRepository = feedbackJpaRepository;
    }

    @Override
    public Feedback save(Feedback feedback) {
        FeedbackJpaEntity entity = FeedbackMapper.toEntity(feedback);
        FeedbackJpaEntity savedEntity = feedbackJpaRepository.save(entity);

        return FeedbackMapper.toDomain(savedEntity);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackJpaRepository.findAll()
                .stream()
                .map(FeedbackMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return feedbackJpaRepository.findById(id)
                .map(FeedbackMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        feedbackJpaRepository.deleteById(id);
    }
}