package com.example.parking.report.adapter.in.web.dto;

import com.example.parking.report.domain.enums.ReportType;
import jakarta.validation.constraints.NotBlank;

public class ReportRequest {

    @NotBlank(message = "Tiêu đề báo cáo không được để trống")
    private String title;

    private ReportType type;

    @NotBlank(message = "Nội dung báo cáo không được để trống")
    private String content;

    private String createdBy;

    public String getTitle() {
        return title;
    }

    public ReportType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}