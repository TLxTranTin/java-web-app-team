package com.example.parking.building.application.service;

import com.example.parking.building.application.port.in.IFloorUseCase;
import com.example.parking.building.application.port.out.IFloorRepositoryPort;
import com.example.parking.building.domain.model.Floor;
import com.example.parking.building.dto.FloorRequest;
import com.example.parking.building.dto.FloorResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloorService implements IFloorUseCase {

    private final IFloorRepositoryPort floorRepositoryPort;

    public FloorService(IFloorRepositoryPort floorRepositoryPort) {
        this.floorRepositoryPort = floorRepositoryPort;
    }

    @Override
    public FloorResponse createFloor(FloorRequest request) {
        Floor floor = new Floor(null, request.getName(), request.getTotalSlots(), request.getBuildingId());
        return mapToResponse(floorRepositoryPort.save(floor));
    }

    @Override
    public FloorResponse getFloorById(Long id) {
        Floor floor = floorRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Floor not found with id: " + id));
        return mapToResponse(floor);
    }

    @Override
    public List<FloorResponse> getFloorsByBuildingId(Long buildingId) {
        return floorRepositoryPort.findByBuildingId(buildingId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FloorResponse updateFloor(Long id, FloorRequest request) {
        Floor existingFloor = floorRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Floor not found with id: " + id));

        existingFloor.setName(request.getName());
        existingFloor.setTotalSlots(request.getTotalSlots());
        existingFloor.setBuildingId(request.getBuildingId());

        return mapToResponse(floorRepositoryPort.save(existingFloor));
    }

    @Override
    public void deleteFloor(Long id) {
        floorRepositoryPort.deleteById(id);
    }

    private FloorResponse mapToResponse(Floor floor) {
        return new FloorResponse(floor.getId(), floor.getName(), floor.getTotalSlots(), floor.getBuildingId());
    }
}