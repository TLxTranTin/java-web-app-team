package com.example.parking.building.application.port.out;

import com.example.parking.building.domain.model.Building;
import java.util.List;
import java.util.Optional;

public interface IBuildingRepositoryPort {
    Building save(Building building);
    Optional<Building> findById(Long id);
    List<Building> findAll();
    void deleteById(Long id);
}