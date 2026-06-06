package com.example.parking.building.adapter.in.web;

import com.example.parking.building.application.port.in.IBuildingUseCase;
import com.example.parking.building.dto.BuildingRequest;
import com.example.parking.building.dto.BuildingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    private final IBuildingUseCase buildingUseCase;

    public BuildingController(IBuildingUseCase buildingUseCase) {
        this.buildingUseCase = buildingUseCase;
    }

    @PostMapping
    public ResponseEntity<BuildingResponse> createBuilding(@RequestBody BuildingRequest request) {
        return ResponseEntity.ok(buildingUseCase.createBuilding(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponse> getBuildingById(@PathVariable Long id) {
        return ResponseEntity.ok(buildingUseCase.getBuildingById(id));
    }

    @GetMapping
    public ResponseEntity<List<BuildingResponse>> getAllBuildings() {
        return ResponseEntity.ok(buildingUseCase.getAllBuildings());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        buildingUseCase.deleteBuilding(id);
        return ResponseEntity.noContent().build();
    }
}