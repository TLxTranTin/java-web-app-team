package com.example.parking.report.domain.model;

import com.example.parking.report.domain.enums.ReportType;

import java.time.LocalDateTime;

public class Report {

    private Long id;
    private String title;
    private ReportType type;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Report(
            Long id,
            String title,
            ReportType type,
            String content,
            String createdBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.type = type == null ? ReportType.SYSTEM : type;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateFrom(Report newData) {
        this.title = newData.getTitle();
        this.type = newData.getType() == null ? this.type : newData.getType();
        this.content = newData.getContent();
        this.createdBy = newData.getCreatedBy();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}