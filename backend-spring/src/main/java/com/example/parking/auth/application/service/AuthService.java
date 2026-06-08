package com.example.parking.auth.application.service;

import com.example.parking.auth.application.port.in.IAuthUseCase;
import com.example.parking.auth.application.port.out.IAuthUserRepositoryPort;
import com.example.parking.auth.application.port.out.IPasswordEncoderPort;
import com.example.parking.auth.domain.model.AuthUser;
import com.example.parking.auth.domain.model.UserRole;
import com.example.parking.auth.dto.AuthResponse;
import com.example.parking.auth.dto.LoginRequest;
import com.example.parking.auth.dto.RegisterRequest;
import com.example.parking.shared.exception.BadRequestException;
import com.example.parking.shared.exception.ConflictException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthUseCase {

    private final IAuthUserRepositoryPort userRepositoryPort;
    private final IPasswordEncoderPort passwordEncoderPort;

    public AuthService(IAuthUserRepositoryPort userRepositoryPort,
                       IPasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepositoryPort.existsByUsername(request.username())) {
            throw new ConflictException("Username '" + request.username() + "' đã tồn tại");
        }

        UserRole role = parseRole(request.role());
        String passwordHash = passwordEncoderPort.encode(request.password());
        AuthUser user = new AuthUser(null, request.username(), passwordHash, role);
        AuthUser saved = userRepositoryPort.save(user);

        return new AuthResponse(saved.getId(), saved.getUsername(),
                saved.getRole().name(), "demo-token-" + saved.getId());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        AuthUser user = userRepositoryPort.findByUsername(request.username())
                .orElseThrow(() -> new BadRequestException("Sai username hoặc password"));

        if (!passwordEncoderPort.matches(request.password(), user.getPasswordHash())) {
            throw new BadRequestException("Sai username hoặc password");
        }

        return new AuthResponse(user.getId(), user.getUsername(),
                user.getRole().name(), "demo-token-" + user.getId());
    }

    private UserRole parseRole(String role) {
        if (role == null || role.isBlank()) return UserRole.STAFF;
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Role không hợp lệ: " + role);
        }
    }
}