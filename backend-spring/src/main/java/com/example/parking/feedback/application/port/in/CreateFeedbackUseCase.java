package com.example.parking.feedback.application.port.in;

import com.example.parking.feedback.domain.model.Feedback;

public interface CreateFeedbackUseCase {
    Feedback createFeedback(Feedback feedback);
}