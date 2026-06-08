package com.example.parking.user.dto;

public record UserResponse(
        Long id,
        String username,
        String role,
        String fullName,
        String email,
        String phone
) {}