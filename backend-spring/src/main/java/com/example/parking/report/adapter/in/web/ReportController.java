package com.example.parking.report.adapter.in.web;

import com.example.parking.report.adapter.in.web.dto.ReportRequest;
import com.example.parking.report.adapter.in.web.dto.ReportResponse;
import com.example.parking.report.application.port.in.CreateReportUseCase;
import com.example.parking.report.application.port.in.DeleteReportUseCase;
import com.example.parking.report.application.port.in.GetReportUseCase;
import com.example.parking.report.application.port.in.UpdateReportUseCase;
import com.example.parking.report.domain.model.Report;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final CreateReportUseCase createReportUseCase;
    private final GetReportUseCase getReportUseCase;
    private final UpdateReportUseCase updateReportUseCase;
    private final DeleteReportUseCase deleteReportUseCase;

    public ReportController(
            CreateReportUseCase createReportUseCase,
            GetReportUseCase getReportUseCase,
            UpdateReportUseCase updateReportUseCase,
            DeleteReportUseCase deleteReportUseCase
    ) {
        this.createReportUseCase = createReportUseCase;
        this.getReportUseCase = getReportUseCase;
        this.updateReportUseCase = updateReportUseCase;
        this.deleteReportUseCase = deleteReportUseCase;
    }

    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@Valid @RequestBody ReportRequest request) {
        Report report = ReportWebMapper.toDomain(request);
        Report createdReport = createReportUseCase.createReport(report);

        return ResponseEntity.ok(ReportWebMapper.toResponse(createdReport));
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReports() {
        List<ReportResponse> responses = getReportUseCase.getAllReports()
                .stream()
                .map(ReportWebMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable Long id) {
        Report report = getReportUseCase.getReportById(id);

        return ResponseEntity.ok(ReportWebMapper.toResponse(report));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport(
            @PathVariable Long id,
            @Valid @RequestBody ReportRequest request
    ) {
        Report report = ReportWebMapper.toDomain(request);
        Report updatedReport = updateReportUseCase.updateReport(report);

        return ResponseEntity.ok(ReportWebMapper.toResponse(updatedReport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        deleteReportUseCase.deleteReport(id);

        return ResponseEntity.noContent().build();
    }
}