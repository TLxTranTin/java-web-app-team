package com.example.parking.feedback.adapter.in.web;

import com.example.parking.feedback.adapter.in.web.dto.FeedbackRequest;
import com.example.parking.feedback.adapter.in.web.dto.FeedbackResponse;
import com.example.parking.feedback.application.port.in.CreateFeedbackUseCase;
import com.example.parking.feedback.application.port.in.DeleteFeedbackUseCase;
import com.example.parking.feedback.application.port.in.GetFeedbackUseCase;
import com.example.parking.feedback.application.port.in.UpdateFeedbackUseCase;
import com.example.parking.feedback.domain.model.Feedback;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final CreateFeedbackUseCase createFeedbackUseCase;
    private final GetFeedbackUseCase getFeedbackUseCase;
    private final UpdateFeedbackUseCase updateFeedbackUseCase;
    private final DeleteFeedbackUseCase deleteFeedbackUseCase;

    public FeedbackController(
            CreateFeedbackUseCase createFeedbackUseCase,
            GetFeedbackUseCase getFeedbackUseCase,
            UpdateFeedbackUseCase updateFeedbackUseCase,
            DeleteFeedbackUseCase deleteFeedbackUseCase
    ) {
        this.createFeedbackUseCase = createFeedbackUseCase;
        this.getFeedbackUseCase = getFeedbackUseCase;
        this.updateFeedbackUseCase = updateFeedbackUseCase;
        this.deleteFeedbackUseCase = deleteFeedbackUseCase;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> createFeedback(@Valid @RequestBody FeedbackRequest request) {
        Feedback feedback = toDomain(request);
        Feedback createdFeedback = createFeedbackUseCase.createFeedback(feedback);

        return ResponseEntity.ok(toResponse(createdFeedback));
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getAllFeedbacks() {
        List<FeedbackResponse> responses = getFeedbackUseCase.getAllFeedbacks()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponse> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = getFeedbackUseCase.getFeedbackById(id);

        return ResponseEntity.ok(toResponse(feedback));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponse> updateFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequest request
    ) {
        Feedback feedback = toDomain(request);
        Feedback updatedFeedback = updateFeedbackUseCase.updateFeedback(id, feedback);

        return ResponseEntity.ok(toResponse(updatedFeedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        deleteFeedbackUseCase.deleteFeedback(id);

        return ResponseEntity.noContent().build();
    }

    private Feedback toDomain(FeedbackRequest request) {
        return new Feedback(
                null,
                request.getCustomerName(),
                request.getPhoneNumber(),
                request.getEmail(),
                request.getContent(),
                request.getRating(),
                request.getStatus(),
                request.getResponseMessage(),
                null,
                null
        );
    }

    private FeedbackResponse toResponse(Feedback feedback) {
        return new FeedbackResponse(
                feedback.getId(),
                feedback.getCustomerName(),
                feedback.getPhoneNumber(),
                feedback.getEmail(),
                feedback.getContent(),
                feedback.getRating(),
                feedback.getStatus(),
                feedback.getResponseMessage(),
                feedback.getCreatedAt(),
                feedback.getUpdatedAt()
        );
    }
}