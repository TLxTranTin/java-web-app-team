package com.example.parking.building.adapter.out.persistence;

import com.example.parking.building.application.port.out.IZoneRepositoryPort;
import com.example.parking.building.domain.model.Zone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ZonePersistenceAdapter implements IZoneRepositoryPort {

    private final IZoneJpaRepository zoneJpaRepository;
    private final IFloorJpaRepository floorJpaRepository;

    public ZonePersistenceAdapter(IZoneJpaRepository zoneJpaRepository, IFloorJpaRepository floorJpaRepository) {
        this.zoneJpaRepository = zoneJpaRepository;
        this.floorJpaRepository = floorJpaRepository;
    }

    @Override
    public Zone save(Zone zone) {
        ZoneEntity entity = new ZoneEntity();
        entity.setId(zone.getId());
        entity.setName(zone.getName());
        entity.setType(zone.getType());
        entity.setTotalSlots(zone.getTotalSlots());

        FloorEntity floorEntity = floorJpaRepository.findById(zone.getFloorId())
                .orElseThrow(() -> new RuntimeException("Floor not found with id: " + zone.getFloorId()));
        entity.setFloor(floorEntity);

        ZoneEntity savedEntity = zoneJpaRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<Zone> findById(Long id) {
        return zoneJpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Zone> findByFloorId(Long floorId) {
        return zoneJpaRepository.findByFloorId(floorId).stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        zoneJpaRepository.deleteById(id);
    }

    private Zone mapToDomain(ZoneEntity entity) {
        Long floorId = (entity.getFloor() != null) ? entity.getFloor().getId() : null;
        return new Zone(entity.getId(), entity.getName(), entity.getType(), entity.getTotalSlots(), floorId);
    }
}