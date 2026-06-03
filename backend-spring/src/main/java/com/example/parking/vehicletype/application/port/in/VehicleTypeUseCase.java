package com.example.parking.vehicletype.application.port.in;

import com.example.parking.vehicletype.domain.model.VehicleType;
import java.util.List;
import java.util.Optional;

public interface VehicleTypeUseCase {
    VehicleType createVehicleType(VehicleType vehicleType);
    Optional<VehicleType> getVehicleTypeById(Long id);
    List<VehicleType> getAllVehicleTypes();
    VehicleType updateVehicleType(Long id, VehicleType vehicleType);
    void deleteVehicleType(Long id);
}