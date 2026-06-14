package com.example.parking.vehicle.application.port.in;

import com.example.parking.vehicle.domain.model.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleUseCase {
    Vehicle createVehicle(Vehicle vehicle);
    Optional<Vehicle> getVehicleById(Long id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getVehiclesByType(Long vehicleTypeId);
    Optional<Vehicle> getVehicleByLicensePlate(String licensePlate);
    Vehicle updateVehicle(Long id, Vehicle vehicle);
    void deleteVehicle(Long id);
}