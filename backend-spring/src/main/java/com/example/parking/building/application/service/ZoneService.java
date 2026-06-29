package com.example.parking.building.application.service;

import com.example.parking.building.application.port.in.IZoneUseCase;
import com.example.parking.building.application.port.out.IZoneRepositoryPort;
import com.example.parking.building.domain.model.Zone;
import com.example.parking.building.dto.ZoneRequest;
import com.example.parking.building.dto.ZoneResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneService implements IZoneUseCase {

    private final IZoneRepositoryPort zoneRepositoryPort;

    public ZoneService(IZoneRepositoryPort zoneRepositoryPort) {
        this.zoneRepositoryPort = zoneRepositoryPort;
    }

    @Override
    public ZoneResponse createZone(ZoneRequest request) {
        Zone zone = new Zone(null, request.getName(), request.getType(), request.getTotalSlots(), request.getFloorId());
        return mapToResponse(zoneRepositoryPort.save(zone));
    }

    @Override
    public ZoneResponse getZoneById(Long id) {
        Zone zone = zoneRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found with id: " + id));
        return mapToResponse(zone);
    }

    @Override
    public List<ZoneResponse> getZonesByFloorId(Long floorId) {
        return zoneRepositoryPort.findByFloorId(floorId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ZoneResponse updateZone(Long id, ZoneRequest request) {
        Zone existingZone = zoneRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found with id: " + id));

        existingZone.setName(request.getName());
        existingZone.setType(request.getType());
        existingZone.setTotalSlots(request.getTotalSlots());
        existingZone.setFloorId(request.getFloorId());

        return mapToResponse(zoneRepositoryPort.save(existingZone));
    }

    @Override
    public void deleteZone(Long id) {
        zoneRepositoryPort.deleteById(id);
    }

    private ZoneResponse mapToResponse(Zone zone) {
        return new ZoneResponse(zone.getId(), zone.getName(), zone.getType(), zone.getTotalSlots(), zone.getFloorId());
    }
}