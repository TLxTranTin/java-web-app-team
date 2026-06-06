package com.example.parking.feedback.adapter.in.web.dto;

import com.example.parking.feedback.domain.enums.FeedbackStatus;

import java.time.LocalDateTime;

public class FeedbackResponse {

    private Long id;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String content;
    private Integer rating;
    private FeedbackStatus status;
    private String responseMessage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedbackResponse(
            Long id,
            String customerName,
            String phoneNumber,
            String email,
            String content,
            Integer rating,
            FeedbackStatus status,
            String responseMessage,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.content = content;
        this.rating = rating;
        this.status = status;
        this.responseMessage = responseMessage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return content;
    }

    public Integer getRating() {
        return rating;
    }

    public FeedbackStatus getStatus() {
        return status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}