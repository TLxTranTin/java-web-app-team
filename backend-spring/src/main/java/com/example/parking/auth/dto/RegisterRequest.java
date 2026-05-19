package com.example.parking.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "Username không được để trống")
        String username,

        @NotBlank(message = "Password không được để trống")
        String password,

        String role
) {
}