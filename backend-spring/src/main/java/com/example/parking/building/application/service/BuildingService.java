package com.example.parking.building.application.service;

import com.example.parking.building.application.port.in.IBuildingUseCase;
import com.example.parking.building.application.port.out.IBuildingRepositoryPort;
import com.example.parking.building.domain.model.Building;
import com.example.parking.building.dto.BuildingRequest;
import com.example.parking.building.dto.BuildingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingUseCase {

    private final IBuildingRepositoryPort buildingRepositoryPort;

    public BuildingService(IBuildingRepositoryPort buildingRepositoryPort) {
        this.buildingRepositoryPort = buildingRepositoryPort;
    }

    @Override
    public BuildingResponse createBuilding(BuildingRequest request) {
        Building building = new Building(null, request.getName(), request.getAddress(), request.getDescription());
        Building savedBuilding = buildingRepositoryPort.save(building);
        return mapToResponse(savedBuilding);
    }

    @Override
    public BuildingResponse getBuildingById(Long id) {
        Building building = buildingRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));
        return mapToResponse(building);
    }

    @Override
    public List<BuildingResponse> getAllBuildings() {
        return buildingRepositoryPort.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBuilding(Long id) {
        buildingRepositoryPort.deleteById(id);
    }

    private BuildingResponse mapToResponse(Building building) {
        return new BuildingResponse(building.getId(), building.getName(), building.getAddress(), building.getDescription());
    }
}