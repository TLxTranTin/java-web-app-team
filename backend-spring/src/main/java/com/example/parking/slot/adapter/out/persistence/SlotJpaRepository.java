package com.example.parking.slot.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SlotJpaRepository extends JpaRepository<SlotEntity, Long> {
    List<SlotEntity> findByStatus(SlotEntity.SlotStatus status);
    List<SlotEntity> findByFloor(Integer floor);
    List<SlotEntity> findBySection(String section);
}