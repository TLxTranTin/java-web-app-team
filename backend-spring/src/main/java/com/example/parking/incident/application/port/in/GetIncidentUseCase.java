package com.example.parking.incident.application.port.in;

import com.example.parking.incident.domain.model.Incident;

import java.util.List;

public interface GetIncidentUseCase {
    List<Incident> getAllIncidents();

    Incident getIncidentById(Long id);
}