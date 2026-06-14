package com.example.parking.building.adapter.out.persistence;

import com.example.parking.building.application.port.out.IFloorRepositoryPort;
import com.example.parking.building.domain.model.Floor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FloorPersistenceAdapter implements IFloorRepositoryPort {

    private final IFloorJpaRepository floorJpaRepository;
    private final IBuildingJpaRepository buildingJpaRepository;

    public FloorPersistenceAdapter(IFloorJpaRepository floorJpaRepository, IBuildingJpaRepository buildingJpaRepository) {
        this.floorJpaRepository = floorJpaRepository;
        this.buildingJpaRepository = buildingJpaRepository;
    }

    @Override
    public Floor save(Floor floor) {
        FloorEntity entity = new FloorEntity();
        entity.setId(floor.getId());
        entity.setName(floor.getName());
        entity.setTotalSlots(floor.getTotalSlots());
        
        BuildingEntity buildingEntity = buildingJpaRepository.findById(floor.getBuildingId())
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + floor.getBuildingId()));
        entity.setBuilding(buildingEntity);

        FloorEntity savedEntity = floorJpaRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<Floor> findById(Long id) {
        return floorJpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Floor> findByBuildingId(Long buildingId) {
        return floorJpaRepository.findByBuildingId(buildingId).stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        floorJpaRepository.deleteById(id);
    }

    private Floor mapToDomain(FloorEntity entity) {
        Long buildingId = (entity.getBuilding() != null) ? entity.getBuilding().getId() : null;
        return new Floor(entity.getId(), entity.getName(), entity.getTotalSlots(), buildingId);
    }
}