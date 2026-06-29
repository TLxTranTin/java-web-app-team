package com.example.parking.user.application.service;

import com.example.parking.auth.application.port.out.IPasswordEncoderPort;
import com.example.parking.auth.domain.model.UserRole;
import com.example.parking.shared.exception.ConflictException;
import com.example.parking.shared.exception.NotFoundException;
import com.example.parking.shared.exception.BadRequestException;
import com.example.parking.user.application.port.in.IUserUseCase;
import com.example.parking.user.application.port.out.IUserRepositoryPort;
import com.example.parking.user.domain.model.User;
import com.example.parking.user.dto.CreateUserRequest;
import com.example.parking.user.dto.UpdateUserRequest;
import com.example.parking.user.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserUseCase {

    private final IUserRepositoryPort userRepositoryPort;
    private final IPasswordEncoderPort passwordEncoderPort;

    public UserService(IUserRepositoryPort userRepositoryPort,
                       IPasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepositoryPort.existsByUsername(request.username())) {
            throw new ConflictException("Username '" + request.username() + "' đã tồn tại");
        }

        UserRole role = parseRole(request.role());
        String passwordHash = passwordEncoderPort.encode(request.password());

        User user = new User(null, request.username(), passwordHash, role,
                request.fullName(), request.email(), request.phone());

        return toResponse(userRepositoryPort.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepositoryPort.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user với id: " + id));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepositoryPort.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User existing = userRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user với id: " + id));

        UserRole role = (request.role() != null && !request.role().isBlank())
                ? parseRole(request.role())
                : existing.getRole();

        String passwordHash = (request.newPassword() != null && !request.newPassword().isBlank())
                ? passwordEncoderPort.encode(request.newPassword())
                : existing.getPasswordHash();

        String fullName = request.fullName() != null ? request.fullName() : existing.getFullName();
        String email    = request.email()    != null ? request.email()    : existing.getEmail();
        String phone    = request.phone()    != null ? request.phone()    : existing.getPhone();

        User updated = new User(id, existing.getUsername(), passwordHash, role, fullName, email, phone);

        return toResponse(userRepositoryPort.save(updated));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepositoryPort.existsById(id)) {
            throw new NotFoundException("Không tìm thấy user với id: " + id);
        }
        userRepositoryPort.deleteById(id);
    }

    private UserRole parseRole(String role) {
        if (role == null || role.isBlank()) return UserRole.STAFF;
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Role không hợp lệ: " + role + ". Chỉ nhận: ADMIN, MANAGER, STAFF, DRIVER");
        }
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole().name(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}