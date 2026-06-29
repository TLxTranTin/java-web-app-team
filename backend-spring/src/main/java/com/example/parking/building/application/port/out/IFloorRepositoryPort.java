package com.example.parking.building.application.port.out;

import com.example.parking.building.domain.model.Floor;
import java.util.List;
import java.util.Optional;

public interface IFloorRepositoryPort {
    Floor save(Floor floor);
    Optional<Floor> findById(Long id);
    List<Floor> findByBuildingId(Long buildingId);
    void deleteById(Long id);
}