package com.example.parking.building.domain.model;

public class Floor {
    private Long id;
    private String name;
    private Integer totalSlots;
    private Long buildingId;

    public Floor() {}

    public Floor(Long id, String name, Integer totalSlots, Long buildingId) {
        this.id = id;
        this.name = name;
        this.totalSlots = totalSlots;
        this.buildingId = buildingId;
    }

    // Các hàm Getter và Setter bắt buộc phải có dưới đây
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getTotalSlots() { return totalSlots; }
    public void setTotalSlots(Integer totalSlots) { this.totalSlots = totalSlots; }
    public Long getBuildingId() { return buildingId; }
    public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
}