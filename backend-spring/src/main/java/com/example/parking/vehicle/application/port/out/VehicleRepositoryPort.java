package com.example.parking.vehicle.application.port.out;

import com.example.parking.vehicle.domain.model.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleRepositoryPort {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    List<Vehicle> findAll();
    List<Vehicle> findByVehicleTypeId(Long vehicleTypeId);
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    void deleteById(Long id);
    boolean existsById(Long id);
}