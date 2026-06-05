package com.example.parking.role.application.service;

import com.example.parking.shared.exception.ConflictException;
import com.example.parking.shared.exception.NotFoundException;
import com.example.parking.role.application.port.in.IRoleUseCase;
import com.example.parking.role.application.port.out.IRoleRepositoryPort;
import com.example.parking.role.domain.model.Role;
import com.example.parking.role.dto.CreateRoleRequest;
import com.example.parking.role.dto.RoleResponse;
import com.example.parking.role.dto.UpdateRoleRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleUseCase {

    private final IRoleRepositoryPort roleRepositoryPort;

    public RoleService(IRoleRepositoryPort roleRepositoryPort) {
        this.roleRepositoryPort = roleRepositoryPort;
    }

    @Override
    public RoleResponse createRole(CreateRoleRequest request) {
        String normalizedName = request.name().toUpperCase();

        if (roleRepositoryPort.existsByName(normalizedName)) {
            throw new ConflictException("Role '" + normalizedName + "' đã tồn tại");
        }

        Role role = new Role(null, normalizedName, request.description());
        return toResponse(roleRepositoryPort.save(role));
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        return roleRepositoryPort.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy role với id: " + id));
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepositoryPort.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse updateRole(Long id, UpdateRoleRequest request) {
        Role existing = roleRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy role với id: " + id));

        String newName = (request.name() != null && !request.name().isBlank())
                ? request.name().toUpperCase()
                : existing.getName();

        if (!newName.equals(existing.getName()) && roleRepositoryPort.existsByName(newName)) {
            throw new ConflictException("Role '" + newName + "' đã tồn tại");
        }

        String newDesc = request.description() != null ? request.description() : existing.getDescription();

        Role updated = new Role(id, newName, newDesc);
        return toResponse(roleRepositoryPort.save(updated));
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepositoryPort.existsById(id)) {
            throw new NotFoundException("Không tìm thấy role với id: " + id);
        }
        roleRepositoryPort.deleteById(id);
    }

    private RoleResponse toResponse(Role role) {
        return new RoleResponse(role.getId(), role.getName(), role.getDescription());
    }
}