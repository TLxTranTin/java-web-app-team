package com.example.parking.incident.application.port.in;

import com.example.parking.incident.domain.model.Incident;

public interface UpdateIncidentUseCase {
    Incident updateIncident(Long id, Incident incident);
}