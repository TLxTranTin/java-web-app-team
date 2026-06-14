package com.example.parking.building.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuildingJpaRepository extends JpaRepository<BuildingEntity, Long> {
}