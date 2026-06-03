package com.example.parking.slot.application.service;

import com.example.parking.slot.application.port.in.SlotUseCase;
import com.example.parking.slot.application.port.out.SlotRepositoryPort;
import com.example.parking.slot.domain.model.Slot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SlotService implements SlotUseCase {
    
    private final SlotRepositoryPort slotRepository;
    
    public SlotService(SlotRepositoryPort slotRepository) {
        this.slotRepository = slotRepository;
    }
    
    @Override
    public Slot createSlot(Slot slot) {
        if (slot.getStatus() == null) {
            slot.setStatus(Slot.SlotStatus.AVAILABLE);
        }
        return slotRepository.save(slot);
    }
    
    @Override
    public Optional<Slot> getSlotById(Long id) {
        return slotRepository.findById(id);
    }
    
    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }
    
    @Override
    public List<Slot> getAvailableSlots() {
        return slotRepository.findByStatus(Slot.SlotStatus.AVAILABLE);
    }
    
    @Override
    public List<Slot> getSlotsByFloor(Integer floor) {
        return slotRepository.findByFloor(floor);
    }
    
    @Override
    public List<Slot> getSlotsBySection(String section) {
        return slotRepository.findBySection(section);
    }
    
    @Override
    public Slot updateSlot(Long id, Slot slot) {
        if (!slotRepository.existsById(id)) {
            throw new RuntimeException("Slot not found with id: " + id);
        }
        slot.setId(id);
        return slotRepository.save(slot);
    }
    
    @Override
    public Slot updateSlotStatus(Long id, Slot.SlotStatus status) {
        Optional<Slot> slotOpt = slotRepository.findById(id);
        if (slotOpt.isEmpty()) {
            throw new RuntimeException("Slot not found with id: " + id);
        }
        Slot slot = slotOpt.get();
        slot.setStatus(status);
        return slotRepository.save(slot);
    }
    
    @Override
    public void deleteSlot(Long id) {
        if (!slotRepository.existsById(id)) {
            throw new RuntimeException("Slot not found with id: " + id);
        }
        slotRepository.deleteById(id);
    }
}