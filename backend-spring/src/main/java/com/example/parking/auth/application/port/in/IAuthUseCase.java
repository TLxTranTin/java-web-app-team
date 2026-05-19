package com.example.parking.auth.application.port.in;

import com.example.parking.auth.dto.AuthResponse;
import com.example.parking.auth.dto.LoginRequest;
import com.example.parking.auth.dto.RegisterRequest;

public interface IAuthUseCase {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}