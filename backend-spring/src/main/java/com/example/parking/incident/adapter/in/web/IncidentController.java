package com.example.parking.incident.adapter.in.web;

import com.example.parking.incident.adapter.in.web.dto.IncidentRequest;
import com.example.parking.incident.adapter.in.web.dto.IncidentResponse;
import com.example.parking.incident.application.port.in.CreateIncidentUseCase;
import com.example.parking.incident.application.port.in.DeleteIncidentUseCase;
import com.example.parking.incident.application.port.in.GetIncidentUseCase;
import com.example.parking.incident.application.port.in.UpdateIncidentUseCase;
import com.example.parking.incident.domain.model.Incident;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final CreateIncidentUseCase createIncidentUseCase;
    private final GetIncidentUseCase getIncidentUseCase;
    private final UpdateIncidentUseCase updateIncidentUseCase;
    private final DeleteIncidentUseCase deleteIncidentUseCase;

    public IncidentController(
            CreateIncidentUseCase createIncidentUseCase,
            GetIncidentUseCase getIncidentUseCase,
            UpdateIncidentUseCase updateIncidentUseCase,
            DeleteIncidentUseCase deleteIncidentUseCase
    ) {
        this.createIncidentUseCase = createIncidentUseCase;
        this.getIncidentUseCase = getIncidentUseCase;
        this.updateIncidentUseCase = updateIncidentUseCase;
        this.deleteIncidentUseCase = deleteIncidentUseCase;
    }

    @PostMapping
    public ResponseEntity<IncidentResponse> createIncident(@Valid @RequestBody IncidentRequest request) {
        Incident incident = toDomain(request);
        Incident createdIncident = createIncidentUseCase.createIncident(incident);

        return ResponseEntity.ok(toResponse(createdIncident));
    }

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAllIncidents() {
        List<IncidentResponse> responses = getIncidentUseCase.getAllIncidents()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getIncidentById(@PathVariable Long id) {
        Incident incident = getIncidentUseCase.getIncidentById(id);

        return ResponseEntity.ok(toResponse(incident));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidentResponse> updateIncident(
            @PathVariable Long id,
            @Valid @RequestBody IncidentRequest request
    ) {
        Incident incident = toDomain(request);
        Incident updatedIncident = updateIncidentUseCase.updateIncident(id, incident);

        return ResponseEntity.ok(toResponse(updatedIncident));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        deleteIncidentUseCase.deleteIncident(id);

        return ResponseEntity.noContent().build();
    }

    private Incident toDomain(IncidentRequest request) {
        return new Incident(
                null,
                request.getTitle(),
                request.getDescription(),
                request.getType(),
                request.getStatus(),
                request.getReportedBy(),
                request.getVehiclePlate(),
                request.getSlotCode(),
                null,
                null
        );
    }

    private IncidentResponse toResponse(Incident incident) {
        return new IncidentResponse(
                incident.getId(),
                incident.getTitle(),
                incident.getDescription(),
                incident.getType(),
                incident.getStatus(),
                incident.getReportedBy(),
                incident.getVehiclePlate(),
                incident.getSlotCode(),
                incident.getCreatedAt(),
                incident.getUpdatedAt()
        );
    }
}