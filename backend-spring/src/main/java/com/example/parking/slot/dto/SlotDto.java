package com.example.parking.slot.dto;

public class SlotDto {
    private Long id;
    private String slotNumber;
    private Integer floor;
    private String section;
    private String status;
    private Long vehicleTypeId;
    
    public SlotDto() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSlotNumber() {
        return slotNumber;
    }
    
    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }
    
    public Integer getFloor() {
        return floor;
    }
    
    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    
    public String getSection() {
        return section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }
    
    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}