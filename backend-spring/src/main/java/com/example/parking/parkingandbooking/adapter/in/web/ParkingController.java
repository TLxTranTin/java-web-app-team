package com.example.parking.parkingandbooking.adapter.in.web;
import org.springframework.web.bind.annotation.*;

import com.example.parking.parkingandbooking.dto.CheckInRequest;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    @PostMapping("/checkin")
    public String checkIn(@RequestBody CheckInRequest request) {
        return "Xe" + request.getLicensePlate() + "vao bai thanh cong";
    }
}
