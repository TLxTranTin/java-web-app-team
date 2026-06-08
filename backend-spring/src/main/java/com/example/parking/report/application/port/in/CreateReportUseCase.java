package com.example.parking.report.application.port.in;

import com.example.parking.report.domain.model.Report;

public interface CreateReportUseCase {
    Report createReport(Report report);

    Report updateReport(Long id, Report newData);
    
}
