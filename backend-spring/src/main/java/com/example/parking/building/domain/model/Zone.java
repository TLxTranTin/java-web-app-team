package com.example.parking.building.domain.model;

public class Zone {
    private Long id;
    private Long floorId;
    private String zoneName;
    private String vehicleType; // CAR, MOTORBIKE, ELECTRIC
    private Integer maxCapacity;

    public Zone() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFloorId() { return floorId; }
    public void setFloorId(Long floorId) { this.floorId = floorId; }
    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public Integer getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(Integer maxCapacity) { this.maxCapacity = maxCapacity; }
}