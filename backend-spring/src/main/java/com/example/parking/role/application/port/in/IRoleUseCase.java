package com.example.parking.role.application.port.in;

import com.example.parking.role.dto.CreateRoleRequest;
import com.example.parking.role.dto.RoleResponse;
import com.example.parking.role.dto.UpdateRoleRequest;

import java.util.List;

public interface IRoleUseCase {
    RoleResponse createRole(CreateRoleRequest request);
    RoleResponse getRoleById(Long id);
    List<RoleResponse> getAllRoles();
    RoleResponse updateRole(Long id, UpdateRoleRequest request);
    void deleteRole(Long id);
}