package com.example.parking.vehicle.application.service;

import com.example.parking.vehicle.application.port.in.VehicleUseCase;
import com.example.parking.vehicle.application.port.out.VehicleRepositoryPort;
import com.example.parking.vehicle.domain.model.Vehicle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleService implements VehicleUseCase {
    
    private final VehicleRepositoryPort vehicleRepository;
    
    public VehicleService(VehicleRepositoryPort vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        vehicle.setCreatedAt(LocalDateTime.now());
        vehicle.setUpdatedAt(LocalDateTime.now());
        return vehicleRepository.save(vehicle);
    }
    
    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }
    
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
    
    @Override
    public List<Vehicle> getVehiclesByType(Long vehicleTypeId) {
        return vehicleRepository.findByVehicleTypeId(vehicleTypeId);
    }
    
    @Override
    public Optional<Vehicle> getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate);
    }
    
    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
        vehicle.setId(id);
        vehicle.setUpdatedAt(LocalDateTime.now());
        return vehicleRepository.save(vehicle);
    }
    
    @Override
    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }
}