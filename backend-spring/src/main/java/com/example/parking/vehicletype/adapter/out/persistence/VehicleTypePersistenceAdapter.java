package com.example.parking.vehicletype.adapter.out.persistence;

import com.example.parking.vehicletype.application.port.out.VehicleTypeRepositoryPort;
import com.example.parking.vehicletype.domain.model.VehicleType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VehicleTypePersistenceAdapter implements VehicleTypeRepositoryPort {
    
    private final VehicleTypeJpaRepository jpaRepository;
    
    public VehicleTypePersistenceAdapter(VehicleTypeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public VehicleType save(VehicleType vehicleType) {
        VehicleTypeEntity entity = toEntity(vehicleType);
        VehicleTypeEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<VehicleType> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }
    
    @Override
    public List<VehicleType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
    
    private VehicleTypeEntity toEntity(VehicleType domain) {
        VehicleTypeEntity entity = new VehicleTypeEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setRatePerHour(domain.getRatePerHour());
        return entity;
    }
    
    private VehicleType toDomain(VehicleTypeEntity entity) {
        VehicleType domain = new VehicleType();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        domain.setRatePerHour(entity.getRatePerHour());
        return domain;
    }
}