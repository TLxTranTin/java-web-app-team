package com.example.parking.incident.adapter.out.persistence;

import com.example.parking.incident.domain.model.Incident;

public class IncidentMapper {

    private IncidentMapper() {
    }

    public static Incident toDomain(IncidentJpaEntity entity) {
        return new Incident(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getType(),
                entity.getStatus(),
                entity.getReportedBy(),
                entity.getVehiclePlate(),
                entity.getSlotCode(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static IncidentJpaEntity toEntity(Incident incident) {
        IncidentJpaEntity entity = new IncidentJpaEntity();

        entity.setId(incident.getId());
        entity.setTitle(incident.getTitle());
        entity.setDescription(incident.getDescription());
        entity.setType(incident.getType());
        entity.setStatus(incident.getStatus());
        entity.setReportedBy(incident.getReportedBy());
        entity.setVehiclePlate(incident.getVehiclePlate());
        entity.setSlotCode(incident.getSlotCode());
        entity.setCreatedAt(incident.getCreatedAt());
        entity.setUpdatedAt(incident.getUpdatedAt());

        return entity;
    }
}