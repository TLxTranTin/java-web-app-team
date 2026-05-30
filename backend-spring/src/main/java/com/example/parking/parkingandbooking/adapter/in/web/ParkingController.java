package com.example.parking.parkingandbooking.adapter.in.web;
import org.springframework.web.bind.annotation.*;
import com.example.parking.parkingandbooking.adapter.out.persistence.entity.ParkingRecordEntity;
import com.example.parking.parkingandbooking.adapter.out.persistence.repository.ParkingRepository;
import com.example.parking.parkingandbooking.dto.CheckInRequest;
import com.example.parking.parkingandbooking.dto.CheckOutRequest;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingRepository parkingRepository;

    public ParkingController(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @PostMapping("/checkin")
    public String checkin(@RequestBody CheckInRequest request) {

        ParkingRecordEntity parking = new ParkingRecordEntity();

        parking.setPlateNumber(request.getLicensePlate());
        parking.setStatus("IN");

        parkingRepository.save(parking);

        return "Xe vào thành công";
    }

    @PostMapping("/checkout")
    public String checkout(
            @RequestBody CheckOutRequest request) {

        ParkingRecordEntity vehicle =
            parkingRepository
            .findFirstByPlateNumberAndStatus(
                request.getLicensePlate(),
                "IN"
            )
            .orElse(null);

        if (vehicle == null) {
            return "Khong tim thay xe";
        }

        vehicle.setStatus("OUT");

        parkingRepository.save(vehicle);

        return "Xe ra thanh cong";
    }
}