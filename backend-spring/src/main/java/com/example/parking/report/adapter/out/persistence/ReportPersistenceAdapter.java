package com.example.parking.report.adapter.out.persistence;

import com.example.parking.report.application.port.out.ReportRepositoryPort;
import com.example.parking.report.domain.model.Report;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReportPersistenceAdapter implements ReportRepositoryPort {

    private final ReportJpaRepository reportJpaRepository;

    public ReportPersistenceAdapter(ReportJpaRepository reportJpaRepository) {
        this.reportJpaRepository = reportJpaRepository;
    }

    @Override
    public Report save(Report report) {
        ReportJpaEntity entity = ReportMapper.toEntity(report);
        ReportJpaEntity savedEntity = reportJpaRepository.save(entity);

        return ReportMapper.toDomain(savedEntity);
    }

    @Override
    public List<Report> findAll() {
        return reportJpaRepository.findAll()
                .stream()
                .map(ReportMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Report> findById(Long id) {
        return reportJpaRepository.findById(id)
                .map(ReportMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        reportJpaRepository.deleteById(id);
    }
}