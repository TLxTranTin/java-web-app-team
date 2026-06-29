package com.example.parking.vehicletype.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeJpaRepository extends JpaRepository<VehicleTypeEntity, Long> {
    boolean existsByName(String name);
}
