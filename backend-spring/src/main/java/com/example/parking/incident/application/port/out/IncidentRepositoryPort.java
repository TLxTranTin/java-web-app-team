package com.example.parking.incident.application.port.out;

import com.example.parking.incident.domain.model.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentRepositoryPort {

    Incident save(Incident incident);

    List<Incident> findAll();

    Optional<Incident> findById(Long id);

    void deleteById(Long id);
}