package com.example.parking.report.application.port.out;

import java.util.List;
import java.util.Optional;

import com.example.parking.report.domain.model.Report;

public interface ReportRepositoryPort {
    Report save(Report report);
    List<Report> findAll();
    Optional<Report> findById(Long id);
    void deleteById(Long id);

    
}
