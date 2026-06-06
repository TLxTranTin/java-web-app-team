package com.example.parking.feedback.application.port.out;

import com.example.parking.feedback.domain.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepositoryPort {

    Feedback save(Feedback feedback);

    List<Feedback> findAll();

    Optional<Feedback> findById(Long id);

    void deleteById(Long id);
}