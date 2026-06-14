package com.example.parking.slot.application.port.out;

import com.example.parking.slot.domain.model.Slot;
import java.util.List;
import java.util.Optional;

public interface SlotRepositoryPort {
    Slot save(Slot slot);
    Optional<Slot> findById(Long id);
    List<Slot> findAll();
    List<Slot> findByStatus(Slot.SlotStatus status);
    List<Slot> findByFloor(Integer floor);
    List<Slot> findBySection(String section);
    void deleteById(Long id);
    boolean existsById(Long id);
}