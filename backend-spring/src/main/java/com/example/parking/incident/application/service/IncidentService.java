package com.example.parking.incident.application.service;

import com.example.parking.incident.application.port.in.CreateIncidentUseCase;
import com.example.parking.incident.application.port.in.DeleteIncidentUseCase;
import com.example.parking.incident.application.port.in.GetIncidentUseCase;
import com.example.parking.incident.application.port.in.UpdateIncidentUseCase;
import com.example.parking.incident.application.port.out.IncidentRepositoryPort;
import com.example.parking.incident.domain.model.Incident;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentService implements
        CreateIncidentUseCase,
        GetIncidentUseCase,
        UpdateIncidentUseCase,
        DeleteIncidentUseCase {

    private final IncidentRepositoryPort incidentRepositoryPort;

    public IncidentService(IncidentRepositoryPort incidentRepositoryPort) {
        this.incidentRepositoryPort = incidentRepositoryPort;
    }

    @Override
    public Incident createIncident(Incident incident) {
        Incident newIncident = new Incident(
                null,
                incident.getTitle(),
                incident.getDescription(),
                incident.getType(),
                incident.getStatus(),
                incident.getReportedBy(),
                incident.getVehiclePlate(),
                incident.getSlotCode(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return incidentRepositoryPort.save(newIncident);
    }

    @Override
    public List<Incident> getAllIncidents() {
        return incidentRepositoryPort.findAll();
    }

    @Override
    public Incident getIncidentById(Long id) {
        return incidentRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sự cố với id: " + id));
    }

    @Override
    public Incident updateIncident(Long id, Incident newData) {
        Incident currentIncident = getIncidentById(id);

        currentIncident.update(
                newData.getTitle(),
                newData.getDescription(),
                newData.getType(),
                newData.getStatus(),
                newData.getReportedBy(),
                newData.getVehiclePlate(),
                newData.getSlotCode()
        );

        return incidentRepositoryPort.save(currentIncident);
    }

    @Override
    public void deleteIncident(Long id) {
        getIncidentById(id);
        incidentRepositoryPort.deleteById(id);
    }
}