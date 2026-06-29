package com.example.parking.vehicletype.adapter.in.web;

import com.example.parking.vehicletype.application.port.in.VehicleTypeUseCase;
import com.example.parking.vehicletype.domain.model.VehicleType;
import com.example.parking.vehicletype.dto.VehicleTypeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-types")
public class VehicleTypeController {
    
    private final VehicleTypeUseCase vehicleTypeUseCase;
    
    public VehicleTypeController(VehicleTypeUseCase vehicleTypeUseCase) {
        this.vehicleTypeUseCase = vehicleTypeUseCase;
    }
    
    @PostMapping
    public ResponseEntity<VehicleType> create(@RequestBody VehicleTypeDto dto) {
        VehicleType vehicleType = toDomain(dto);
        VehicleType created = vehicleTypeUseCase.createVehicleType(vehicleType);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VehicleType> getById(@PathVariable Long id) {
        return vehicleTypeUseCase.getVehicleTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<VehicleType>> getAll() {
        return ResponseEntity.ok(vehicleTypeUseCase.getAllVehicleTypes());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VehicleType> update(@PathVariable Long id, @RequestBody VehicleTypeDto dto) {
        VehicleType vehicleType = toDomain(dto);
        return ResponseEntity.ok(vehicleTypeUseCase.updateVehicleType(id, vehicleType));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleTypeUseCase.deleteVehicleType(id);
        return ResponseEntity.noContent().build();
    }
    
    private VehicleType toDomain(VehicleTypeDto dto) {
        return new VehicleType(dto.getId(), dto.getName(), dto.getDescription(), dto.getRatePerHour());
    }
}