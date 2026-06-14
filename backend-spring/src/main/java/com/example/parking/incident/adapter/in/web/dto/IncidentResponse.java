package com.example.parking.incident.adapter.in.web.dto;



import java.time.LocalDateTime;

import com.example.parking.incident.domain.model.enums.IncidentStatus;
import com.example.parking.incident.domain.model.enums.IncidentType;

public class IncidentResponse {

    private Long id;
    private String title;
    private String description;
    private IncidentType type;
    private IncidentStatus status;
    private String reportedBy;
    private String vehiclePlate;
    private String slotCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public IncidentResponse(
            Long id,
            String title,
            String description,
            IncidentType type,
            IncidentStatus status,
            String reportedBy,
            String vehiclePlate,
            String slotCode,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.reportedBy = reportedBy;
        this.vehiclePlate = vehiclePlate;
        this.slotCode = slotCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public IncidentType getType() {
        return type;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public String getSlotCode() {
        return slotCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}