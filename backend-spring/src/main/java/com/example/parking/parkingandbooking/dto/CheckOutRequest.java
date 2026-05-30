package com.example.parking.parkingandbooking.dto;
import com.example.parking.parkingandbooking.dto.CheckOutRequest;
public class CheckOutRequest {

    private String licensePlate;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}