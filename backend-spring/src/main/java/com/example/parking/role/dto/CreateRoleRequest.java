package com.example.parking.role.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateRoleRequest(
        @NotBlank(message = "Tên role không được để trống")
        @Size(max = 50, message = "Tên role tối đa 50 ký tự")
        String name,

        String description
) {}