package com.example.parking.incident.adapter.out.persistence;

import com.example.parking.incident.application.port.out.IncidentRepositoryPort;
import com.example.parking.incident.domain.model.Incident;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IncidentPersistenceAdapter implements IncidentRepositoryPort {

    private final IncidentJpaRepository incidentJpaRepository;

    public IncidentPersistenceAdapter(IncidentJpaRepository incidentJpaRepository) {
        this.incidentJpaRepository = incidentJpaRepository;
    }

    @Override
    public Incident save(Incident incident) {
        IncidentJpaEntity entity = IncidentMapper.toEntity(incident);
        IncidentJpaEntity savedEntity = incidentJpaRepository.save(entity);
        return IncidentMapper.toDomain(savedEntity);
    }

    @Override
    public List<Incident> findAll() {
        return incidentJpaRepository.findAll()
                .stream()
                .map(IncidentMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Incident> findById(Long id) {
        return incidentJpaRepository.findById(id)
                .map(IncidentMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        incidentJpaRepository.deleteById(id);
    }
}