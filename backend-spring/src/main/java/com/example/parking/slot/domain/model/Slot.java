package com.example.parking.slot.domain.model;

public class Slot {
    private Long id;
    private String slotNumber;
    private Integer floor;
    private String section;
    private SlotStatus status;
    private Long vehicleTypeId;
    
    public enum SlotStatus {
        AVAILABLE,
        OCCUPIED,
        RESERVED,
        MAINTENANCE
    }
    
    public Slot() {
        this.status = SlotStatus.AVAILABLE;
    }
    
    public Slot(Long id, String slotNumber, Integer floor, String section, 
                SlotStatus status, Long vehicleTypeId) {
        this.id = id;
        this.slotNumber = slotNumber;
        this.floor = floor;
        this.section = section;
        this.status = status;
        this.vehicleTypeId = vehicleTypeId;
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
    
    public SlotStatus getStatus() {
        return status;
    }
    
    public void setStatus(SlotStatus status) {
        this.status = status;
    }
    
    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }
    
    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}