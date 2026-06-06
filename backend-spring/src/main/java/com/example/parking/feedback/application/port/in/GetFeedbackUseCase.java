package com.example.parking.feedback.application.port.in;

import com.example.parking.feedback.domain.model.Feedback;

import java.util.List;

public interface GetFeedbackUseCase {
    List<Feedback> getAllFeedbacks();

    Feedback getFeedbackById(Long id);
}