package com.example.parking.vehicle.dto;

public class VehicleDto {
    private Long id;
    private String licensePlate;
    private String color;
    private String brand;
    private String model;
    private Long vehicleTypeId;
    private String vehicleTypeName;
    
    public VehicleDto() {
    }
    
    public VehicleDto(Long id, String licensePlate, String color, String brand, 
                      String model, Long vehicleTypeId, String vehicleTypeName) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.color = color;
        this.brand = brand;
        this.model = model;
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }
    
    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
    
    public String getVehicleTypeName() {
        return vehicleTypeName;
    }
    
    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }
}