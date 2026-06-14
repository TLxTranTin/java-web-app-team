package com.example.parking.report.adapter.in.web;

import com.example.parking.report.adapter.in.web.dto.ReportRequest;
import com.example.parking.report.adapter.in.web.dto.ReportResponse;
import com.example.parking.report.domain.model.Report;

public class ReportWebMapper {

    private ReportWebMapper() {
    }

    public static Report toDomain(ReportRequest request) {
        return new Report(
                null,
                request.getTitle(),
                request.getType(),
                request.getContent(),
                request.getCreatedBy(),
                null,
                null
        );
    }

    public static ReportResponse toResponse(Report report) {
        return new ReportResponse(
                report.getId(),
                report.getTitle(),
                report.getType(),
                report.getContent(),
                report.getCreatedBy(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        );
    }
}