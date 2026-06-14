package com.example.parking.slot.application.port.in;

import com.example.parking.slot.domain.model.Slot;
import java.util.List;
import java.util.Optional;

public interface SlotUseCase {
    Slot createSlot(Slot slot);
    Optional<Slot> getSlotById(Long id);
    List<Slot> getAllSlots();
    List<Slot> getAvailableSlots();
    List<Slot> getSlotsByFloor(Integer floor);
    List<Slot> getSlotsBySection(String section);
    Slot updateSlot(Long id, Slot slot);
    Slot updateSlotStatus(Long id, Slot.SlotStatus status);
    void deleteSlot(Long id);
}