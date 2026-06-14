package com.example.parking.vehicle.adapter.in.web;

import com.example.parking.vehicle.application.port.in.VehicleUseCase;
import com.example.parking.vehicle.domain.model.Vehicle;
import com.example.parking.vehicle.dto.VehicleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    
    private final VehicleUseCase vehicleUseCase;
    
    public VehicleController(VehicleUseCase vehicleUseCase) {
        this.vehicleUseCase = vehicleUseCase;
    }
    
    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody VehicleDto dto) {
        Vehicle vehicle = toDomain(dto);
        Vehicle created = vehicleUseCase.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Long id) {
        return vehicleUseCase.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        return ResponseEntity.ok(vehicleUseCase.getAllVehicles());
    }
    
    @GetMapping("/license-plate/{plate}")
    public ResponseEntity<Vehicle> getByLicensePlate(@PathVariable String plate) {
        return vehicleUseCase.getVehicleByLicensePlate(plate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/type/{vehicleTypeId}")
    public ResponseEntity<List<Vehicle>> getByType(@PathVariable Long vehicleTypeId) {
        return ResponseEntity.ok(vehicleUseCase.getVehiclesByType(vehicleTypeId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody VehicleDto dto) {
        Vehicle vehicle = toDomain(dto);
        return ResponseEntity.ok(vehicleUseCase.updateVehicle(id, vehicle));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleUseCase.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
    
    private Vehicle toDomain(VehicleDto dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setColor(dto.getColor());
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setVehicleTypeId(dto.getVehicleTypeId());
        vehicle.setVehicleTypeName(dto.getVehicleTypeName());
        return vehicle;
    }
}