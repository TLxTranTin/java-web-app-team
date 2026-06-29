package com.example.parking.building.application.port.in;

import com.example.parking.building.dto.BuildingRequest;
import com.example.parking.building.dto.BuildingResponse;
import java.util.List;

public interface IBuildingUseCase {
    BuildingResponse createBuilding(BuildingRequest request);
    BuildingResponse getBuildingById(Long id);
    List<BuildingResponse> getAllBuildings();
    void deleteBuilding(Long id);
    BuildingResponse updateBuilding(Long id, BuildingRequest request); 
}