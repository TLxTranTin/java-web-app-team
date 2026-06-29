package com.example.parking.vehicletype.dto;

public class VehicleTypeDto {
    private Long id;
    private String name;
    private String description;
    private Double ratePerHour;
    
    public VehicleTypeDto() {
    }
    
    public VehicleTypeDto(Long id, String name, String description, Double ratePerHour) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ratePerHour = ratePerHour;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getRatePerHour() {
        return ratePerHour;
    }
    
    public void setRatePerHour(Double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }
}