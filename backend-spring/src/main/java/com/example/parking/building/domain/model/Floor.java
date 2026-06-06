package com.example.parking.building.domain.model;

public class Floor {
    private Long id;
    private Long buildingId;
    private Integer floorNumber;
    private String floorName;

    public Floor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBuildingId() { return buildingId; }
    public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }
    public String getFloorName() { return floorName; }
    public void setFloorName(String floorName) { this.floorName = floorName; }
}