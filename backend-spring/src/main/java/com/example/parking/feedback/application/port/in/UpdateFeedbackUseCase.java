package com.example.parking.feedback.application.port.in;

import com.example.parking.feedback.domain.model.Feedback;

public interface UpdateFeedbackUseCase {
    Feedback updateFeedback(Long id, Feedback feedback);
}