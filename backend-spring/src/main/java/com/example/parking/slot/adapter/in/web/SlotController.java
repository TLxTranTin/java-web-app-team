package com.example.parking.slot.adapter.in.web;

import com.example.parking.slot.application.port.in.SlotUseCase;
import com.example.parking.slot.domain.model.Slot;
import com.example.parking.slot.dto.SlotDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {
    
    private final SlotUseCase slotUseCase;
    
    public SlotController(SlotUseCase slotUseCase) {
        this.slotUseCase = slotUseCase;
    }
    
    @PostMapping
    public ResponseEntity<Slot> create(@RequestBody SlotDto dto) {
        Slot slot = toDomain(dto);
        Slot created = slotUseCase.createSlot(slot);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Slot> getById(@PathVariable Long id) {
        return slotUseCase.getSlotById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Slot>> getAll() {
        return ResponseEntity.ok(slotUseCase.getAllSlots());
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Slot>> getAvailable() {
        return ResponseEntity.ok(slotUseCase.getAvailableSlots());
    }
    
    @GetMapping("/floor/{floor}")
    public ResponseEntity<List<Slot>> getByFloor(@PathVariable Integer floor) {
        return ResponseEntity.ok(slotUseCase.getSlotsByFloor(floor));
    }
    
    @GetMapping("/section/{section}")
    public ResponseEntity<List<Slot>> getBySection(@PathVariable String section) {
        return ResponseEntity.ok(slotUseCase.getSlotsBySection(section));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Slot> update(@PathVariable Long id, @RequestBody SlotDto dto) {
        Slot slot = toDomain(dto);
        return ResponseEntity.ok(slotUseCase.updateSlot(id, slot));
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<Slot> updateStatus(@PathVariable Long id, @RequestBody SlotDto dto) {
        Slot.SlotStatus status = Slot.SlotStatus.valueOf(dto.getStatus());
        return ResponseEntity.ok(slotUseCase.updateSlotStatus(id, status));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        slotUseCase.deleteSlot(id);
        return ResponseEntity.noContent().build();
    }
    
    private Slot toDomain(SlotDto dto) {
        Slot slot = new Slot();
        slot.setId(dto.getId());
        slot.setSlotNumber(dto.getSlotNumber());
        slot.setFloor(dto.getFloor());
        slot.setSection(dto.getSection());
        if (dto.getStatus() != null) {
            slot.setStatus(Slot.SlotStatus.valueOf(dto.getStatus()));
        }
        slot.setVehicleTypeId(dto.getVehicleTypeId());
        return slot;
    }
}