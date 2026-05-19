package com.example.parking.auth.adapter.in.web;

import com.example.parking.auth.application.port.in.IAuthUseCase;
import com.example.parking.auth.dto.AuthResponse;
import com.example.parking.auth.dto.LoginRequest;
import com.example.parking.auth.dto.RegisterRequest;
import com.example.parking.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthUseCase authUseCase;

    public AuthController(IAuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authUseCase.register(request);
        return ApiResponse.success("Đăng ký thành công", response);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authUseCase.login(request);
        return ApiResponse.success("Đăng nhập thành công", response);
    }
}