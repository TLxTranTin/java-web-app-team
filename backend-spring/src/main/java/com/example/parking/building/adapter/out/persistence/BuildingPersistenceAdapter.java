package com.example.parking.building.adapter.out.persistence;

import com.example.parking.building.application.port.out.IBuildingRepositoryPort;
import com.example.parking.building.domain.model.Building;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BuildingPersistenceAdapter implements IBuildingRepositoryPort {

    private final IBuildingJpaRepository buildingJpaRepository;

    public BuildingPersistenceAdapter(IBuildingJpaRepository buildingJpaRepository) {
        this.buildingJpaRepository = buildingJpaRepository;
    }

    @Override
    public Building save(Building building) {
        BuildingEntity entity = new BuildingEntity();
        entity.setId(building.getId());
        entity.setName(building.getName());
        entity.setAddress(building.getAddress());
        entity.setDescription(building.getDescription());
        
        BuildingEntity savedEntity = buildingJpaRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<Building> findById(Long id) {
        return buildingJpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Building> findAll() {
        return buildingJpaRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        buildingJpaRepository.deleteById(id);
    }

    // Hàm helper map dữ liệu từ Entity sang Domain
    private Building mapToDomain(BuildingEntity entity) {
        return new Building(entity.getId(), entity.getName(), entity.getAddress(), entity.getDescription());
    }
}