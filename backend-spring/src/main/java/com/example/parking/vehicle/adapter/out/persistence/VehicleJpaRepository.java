package com.example.parking.vehicle.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleJpaRepository extends JpaRepository<VehicleEntity, Long> {
    List<VehicleEntity> findByVehicleTypeId(Long vehicleTypeId);
    Optional<VehicleEntity> findByLicensePlate(String licensePlate);
}