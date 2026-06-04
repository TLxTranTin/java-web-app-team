package com.example.parking.incident.adapter.in.web.dto;


import com.example.parking.incident.domain.model.enums.IncidentStatus;
import com.example.parking.incident.domain.model.enums.IncidentType;

import jakarta.validation.constraints.NotBlank;

public class IncidentRequest {

    @NotBlank(message = "Tiêu đề sự cố không được để trống")
    private String title;

    private String description;
    private IncidentType type;
    private IncidentStatus status;
    private String reportedBy;
    private String vehiclePlate;
    private String slotCode;

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
}