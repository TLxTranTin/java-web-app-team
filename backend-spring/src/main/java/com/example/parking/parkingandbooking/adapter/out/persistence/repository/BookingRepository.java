package com.example.parking.parkingandbooking.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.parking.parkingandbooking.adapter.out.persistence.entity.BookingEntity;
import java.util.Optional;
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    
    Optional<BookingEntity> findBySlotNumber(Integer slotNumber);
}