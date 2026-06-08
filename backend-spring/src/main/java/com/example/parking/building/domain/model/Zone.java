package com.example.parking.building.domain.model;

public class Zone {
    private Long id;
    private String name;
    private String type;
    private Integer totalSlots;
    private Long floorId;

    public Zone() {}

    public Zone(Long id, String name, String type, Integer totalSlots, Long floorId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.totalSlots = totalSlots;
        this.floorId = floorId;
    }

    // Các hàm Getter và Setter bắt buộc phải có dưới đây
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getTotalSlots() { return totalSlots; }
    public void setTotalSlots(Integer totalSlots) { this.totalSlots = totalSlots; }
    public Long getFloorId() { return floorId; }
    public void setFloorId(Long floorId) { this.floorId = floorId; }
}