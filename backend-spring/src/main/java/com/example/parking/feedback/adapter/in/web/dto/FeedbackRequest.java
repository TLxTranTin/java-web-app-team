package com.example.parking.feedback.adapter.in.web.dto;

import com.example.parking.feedback.domain.enums.FeedbackStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class FeedbackRequest {

    private String customerName;

    private String phoneNumber;

    private String email;

    @NotBlank(message = "Nội dung phản hồi không được để trống")
    private String content;

    @Min(value = 1, message = "Đánh giá thấp nhất là 1")
    @Max(value = 5, message = "Đánh giá cao nhất là 5")
    private Integer rating;

    private FeedbackStatus status;

    private String responseMessage;

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
}