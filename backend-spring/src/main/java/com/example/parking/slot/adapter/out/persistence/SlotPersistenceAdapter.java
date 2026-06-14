package com.example.parking.slot.adapter.out.persistence;

import com.example.parking.slot.application.port.out.SlotRepositoryPort;
import com.example.parking.slot.domain.model.Slot;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SlotPersistenceAdapter implements SlotRepositoryPort {
    
    private final SlotJpaRepository jpaRepository;
    
    public SlotPersistenceAdapter(SlotJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public Slot save(Slot slot) {
        SlotEntity entity = toEntity(slot);
        SlotEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<Slot> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }
    
    @Override
    public List<Slot> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Slot> findByStatus(Slot.SlotStatus status) {
        SlotEntity.SlotStatus entityStatus = SlotEntity.SlotStatus.valueOf(status.name());
        return jpaRepository.findByStatus(entityStatus)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Slot> findByFloor(Integer floor) {
        return jpaRepository.findByFloor(floor)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Slot> findBySection(String section) {
        return jpaRepository.findBySection(section)
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
    
    private SlotEntity toEntity(Slot domain) {
        SlotEntity entity = new SlotEntity();
        entity.setId(domain.getId());
        entity.setSlotNumber(domain.getSlotNumber());
        entity.setFloor(domain.getFloor());
        entity.setSection(domain.getSection());
        entity.setStatus(SlotEntity.SlotStatus.valueOf(domain.getStatus().name()));
        entity.setVehicleTypeId(domain.getVehicleTypeId());
        return entity;
    }
    
    private Slot toDomain(SlotEntity entity) {
        Slot domain = new Slot();
        domain.setId(entity.getId());
        domain.setSlotNumber(entity.getSlotNumber());
        domain.setFloor(entity.getFloor());
        domain.setSection(entity.getSection());
        domain.setStatus(Slot.SlotStatus.valueOf(entity.getStatus().name()));
        domain.setVehicleTypeId(entity.getVehicleTypeId());
        return domain;
    }
}