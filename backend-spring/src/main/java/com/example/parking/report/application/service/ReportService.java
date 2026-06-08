package com.example.parking.report.application.service;

import com.example.parking.report.application.port.in.CreateReportUseCase;
import com.example.parking.report.application.port.in.DeleteReportUseCase;
import com.example.parking.report.application.port.in.GetReportUseCase;
import com.example.parking.report.application.port.in.UpdateReportUseCase;
import com.example.parking.report.application.port.out.ReportRepositoryPort;
import com.example.parking.report.domain.model.Report;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService implements
        CreateReportUseCase,
        GetReportUseCase,
        UpdateReportUseCase,
        DeleteReportUseCase {

    private final ReportRepositoryPort reportRepositoryPort;

    public ReportService(ReportRepositoryPort reportRepositoryPort) {
        this.reportRepositoryPort = reportRepositoryPort;
    }

    @Override
    public Report createReport(Report report) {
        Report newReport = new Report(
                null,
                report.getTitle(),
                report.getType(),
                report.getContent(),
                report.getCreatedBy(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return reportRepositoryPort.save(newReport);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepositoryPort.findAll();
    }

    @Override
    public Report getReportById(Long id) {
        return reportRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy báo cáo với id: " + id));
    }

    @Override
    public Report updateReport(Long id, Report newData) {
        Report currentReport = getReportById(id);
        currentReport.updateFrom(newData);

        return reportRepositoryPort.save(currentReport);
    }

    @Override
    public void deleteReport(Long id) {
        getReportById(id);
        reportRepositoryPort.deleteById(id);
    }

    @Override
    public Report updateReport(Report report) {
        throw new UnsupportedOperationException("Unimplemented method 'updateReport'");
    }
}