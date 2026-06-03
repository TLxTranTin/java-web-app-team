package com.example.parking.vehicle.adapter.out.persistence;

import com.example.parking.vehicle.application.port.out.VehicleRepositoryPort;
import com.example.parking.vehicle.domain.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VehiclePersistenceAdapter implements VehicleRepositoryPort {
    
    private final VehicleJpaRepository jpaRepository;
    
    public VehiclePersistenceAdapter(VehicleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleEntity entity = toEntity(vehicle);
        VehicleEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<Vehicle> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }
    
    @Override
    public List<Vehicle> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Vehicle> findByVehicleTypeId(Long vehicleTypeId) {
        return jpaRepository.findByVehicleTypeId(vehicleTypeId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Vehicle> findByLicensePlate(String licensePlate) {
        return jpaRepository.findByLicensePlate(licensePlate).map(this::toDomain);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
    
    private VehicleEntity toEntity(Vehicle domain) {
        VehicleEntity entity = new VehicleEntity();
        entity.setId(domain.getId());
        entity.setLicensePlate(domain.getLicensePlate());
        entity.setColor(domain.getColor());
        entity.setBrand(domain.getBrand());
        entity.setModel(domain.getModel());
        entity.setVehicleTypeId(domain.getVehicleTypeId());
        entity.setVehicleTypeName(domain.getVehicleTypeName());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }
    
    private Vehicle toDomain(VehicleEntity entity) {
        Vehicle domain = new Vehicle();
        domain.setId(entity.getId());
        domain.setLicensePlate(entity.getLicensePlate());
        domain.setColor(entity.getColor());
        domain.setBrand(entity.getBrand());
        domain.setModel(entity.getModel());
        domain.setVehicleTypeId(entity.getVehicleTypeId());
        domain.setVehicleTypeName(entity.getVehicleTypeName());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
}