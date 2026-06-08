package com.example.parking.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "Username không được để trống")
        @Size(min = 3, max = 100, message = "Username phải từ 3-100 ký tự")
        String username,

        @NotBlank(message = "Password không được để trống")
        @Size(min = 6, message = "Password tối thiểu 6 ký tự")
        String password,

        String role,
        String fullName,

        @Email(message = "Email không hợp lệ")
        String email,

        String phone
) {}