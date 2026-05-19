package com.example.parking.auth.dto;

public record AuthResponse(
        Long userId,
        String username,
        String role,
        String token
) {
}