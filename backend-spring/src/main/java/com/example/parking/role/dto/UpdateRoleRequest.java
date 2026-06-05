package com.example.parking.role.dto;

import jakarta.validation.constraints.Size;

public record UpdateRoleRequest(
        @Size(max = 50, message = "Tên role tối đa 50 ký tự")
        String name,

        String description
) {}