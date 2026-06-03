package com.example.parking.vehicletype.application.service;

import com.example.parking.vehicletype.application.port.in.VehicleTypeUseCase;
import com.example.parking.vehicletype.application.port.out.VehicleTypeRepositoryPort;
import com.example.parking.vehicletype.domain.model.VehicleType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleTypeService implements VehicleTypeUseCase {
    
    private final VehicleTypeRepositoryPort vehicleTypeRepository;
    
    public VehicleTypeService(VehicleTypeRepositoryPort vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }
    
    @Override
    public VehicleType createVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }
    
    @Override
    public Optional<VehicleType> getVehicleTypeById(Long id) {
        return vehicleTypeRepository.findById(id);
    }
    
    @Override
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }
    
    @Override
    public VehicleType updateVehicleType(Long id, VehicleType vehicleType) {
        if (!vehicleTypeRepository.existsById(id)) {
            throw new RuntimeException("VehicleType not found with id: " + id);
        }
        vehicleType.setId(id);
        return vehicleTypeRepository.save(vehicleType);
    }
    
    @Override
    public void deleteVehicleType(Long id) {
        if (!vehicleTypeRepository.existsById(id)) {
            throw new RuntimeException("VehicleType not found with id: " + id);
        }
        vehicleTypeRepository.deleteById(id);
    }
}