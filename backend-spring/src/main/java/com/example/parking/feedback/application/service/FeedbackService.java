package com.example.parking.feedback.application.service;

import com.example.parking.feedback.application.port.in.CreateFeedbackUseCase;
import com.example.parking.feedback.application.port.in.DeleteFeedbackUseCase;
import com.example.parking.feedback.application.port.in.GetFeedbackUseCase;
import com.example.parking.feedback.application.port.in.UpdateFeedbackUseCase;
import com.example.parking.feedback.application.port.out.FeedbackRepositoryPort;
import com.example.parking.feedback.domain.model.Feedback;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService implements
        CreateFeedbackUseCase,
        GetFeedbackUseCase,
        UpdateFeedbackUseCase,
        DeleteFeedbackUseCase {

    private final FeedbackRepositoryPort feedbackRepositoryPort;

    public FeedbackService(FeedbackRepositoryPort feedbackRepositoryPort) {
        this.feedbackRepositoryPort = feedbackRepositoryPort;
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        Feedback newFeedback = new Feedback(
                null,
                feedback.getCustomerName(),
                feedback.getPhoneNumber(),
                feedback.getEmail(),
                feedback.getContent(),
                feedback.getRating(),
                feedback.getStatus(),
                feedback.getResponseMessage(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return feedbackRepositoryPort.save(newFeedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepositoryPort.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phản hồi với id: " + id));
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback newData) {
        Feedback currentFeedback = getFeedbackById(id);
        currentFeedback.updateFrom(newData);

        return feedbackRepositoryPort.save(currentFeedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        getFeedbackById(id);
        feedbackRepositoryPort.deleteById(id);
    }
}