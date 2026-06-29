package com.example.parking.user.dto;

import jakarta.validation.constraints.Email;

public record UpdateUserRequest(
        String fullName,

        @Email(message = "Email không hợp lệ")
        String email,

        String phone,
        String role,
        String newPassword
) {}