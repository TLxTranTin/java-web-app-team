package com.example.parking.parkingandbooking.adapter.out.persistence.repository;
import java.util.Optional;
import com.example.parking.parkingandbooking.adapter.out.persistence.entity.ParkingRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository
extends JpaRepository<ParkingRecordEntity, Long> {

    Optional<ParkingRecordEntity>
    findFirstByPlateNumberAndStatus(
        String plateNumber,
        String status
    );
}