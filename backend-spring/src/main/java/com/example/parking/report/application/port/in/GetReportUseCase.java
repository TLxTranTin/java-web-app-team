package com.example.parking.report.application.port.in;

import com.example.parking.report.domain.model.Report;

import java.util.List;
public interface GetReportUseCase {
    List<Report> getAllReports();
    Report getReportById(Long id);
}