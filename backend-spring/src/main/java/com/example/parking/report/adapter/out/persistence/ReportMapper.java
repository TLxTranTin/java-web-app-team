package com.example.parking.report.adapter.out.persistence;

import com.example.parking.report.domain.model.Report;

public class ReportMapper {

    private ReportMapper() {
    }

    public static Report toDomain(ReportJpaEntity entity) {
        return new Report(
                entity.getId(),
                entity.getTitle(),
                entity.getType(),
                entity.getContent(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static ReportJpaEntity toEntity(Report report) {
        ReportJpaEntity entity = new ReportJpaEntity();

        entity.setId(report.getId());
        entity.setTitle(report.getTitle());
        entity.setType(report.getType());
        entity.setContent(report.getContent());
        entity.setCreatedBy(report.getCreatedBy());
        entity.setCreatedAt(report.getCreatedAt());
        entity.setUpdatedAt(report.getUpdatedAt());

        return entity;
    }
}