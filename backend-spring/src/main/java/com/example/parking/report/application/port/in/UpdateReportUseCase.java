package com.example.parking.report.application.port.in;

import com.example.parking.report.domain.model.Report;

public interface UpdateReportUseCase {
    Report updateReport(Report report);
    
}
