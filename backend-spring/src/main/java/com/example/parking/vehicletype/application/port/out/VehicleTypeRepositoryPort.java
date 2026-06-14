package com.example.parking.vehicletype.application.port.out;

import com.example.parking.vehicletype.domain.model.VehicleType;
import java.util.List;
import java.util.Optional;

public interface VehicleTypeRepositoryPort {
    VehicleType save(VehicleType vehicleType);
    Optional<VehicleType> findById(Long id);
    List<VehicleType> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}