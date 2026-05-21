package com.example.parking.auth.application.service;

import com.example.parking.auth.application.port.in.IAuthUseCase;
import com.example.parking.auth.application.port.out.IAuthUserRepositoryPort;
import com.example.parking.auth.application.port.out.IPasswordEncoderPort;
import com.example.parking.auth.domain.model.AuthUser;
import com.example.parking.auth.domain.model.UserRole;
import com.example.parking.auth.dto.AuthResponse;
import com.example.parking.auth.dto.LoginRequest;
import com.example.parking.auth.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthUseCase {

    private final IAuthUserRepositoryPort userRepositoryPort;
    private final IPasswordEncoderPort passwordEncoderPort;

    public AuthService(
            IAuthUserRepositoryPort userRepositoryPort,
            IPasswordEncoderPort passwordEncoderPort
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepositoryPort.existsByUsername(request.username())) {
            throw new RuntimeException("Username đã tồn tại");
        }

        UserRole role = parseRole(request.role());

        String passwordHash = passwordEncoderPort.encode(request.password());

        AuthUser user = new AuthUser(
                null,
                request.username(),
                passwordHash,
                role
        );

        AuthUser savedUser = userRepositoryPort.save(user);

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole().name(),
                "demo-token-" + savedUser.getId()
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        AuthUser user = userRepositoryPort.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Sai username hoặc password"));

        boolean passwordMatched = passwordEncoderPort.matches(
                request.password(),
                user.getPasswordHash()
        );

        if (!passwordMatched) {
            throw new RuntimeException("Sai username hoặc password");
        }

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getRole().name(),
                "demo-token-" + user.getId()
        );
    }

    private UserRole parseRole(String role) {
        if (role == null || role.isBlank()) {
            return UserRole.STAFF;
        }

        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Role không hợp lệ. Chỉ nhận ADMIN, GUARD, RESIDENT");
        }
    }
}