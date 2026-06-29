package com.example.parking.vehicletype.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "vehicle_types")
public class VehicleTypeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    @Column(name = "rate_per_hour", nullable = false)
    private Double ratePerHour;
    
    public VehicleTypeEntity() {
    }
    
    public VehicleTypeEntity(Long id, String name, String description, Double ratePerHour) {
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