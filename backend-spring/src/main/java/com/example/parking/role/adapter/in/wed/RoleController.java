package com.example.parking.role.adapter.in.wed;

import com.example.parking.role.application.port.in.IRoleUseCase;
import com.example.parking.role.dto.CreateRoleRequest;
import com.example.parking.role.dto.RoleResponse;
import com.example.parking.role.dto.UpdateRoleRequest;
import com.example.parking.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final IRoleUseCase roleUseCase;

    public RoleController(IRoleUseCase roleUseCase) {
        this.roleUseCase = roleUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(
            @Valid @RequestBody CreateRoleRequest request) {
        RoleResponse response = roleUseCase.createRole(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo role thành công", response));
    }

    @GetMapping("/{id}")
    public ApiResponse<RoleResponse> getRoleById(@PathVariable Long id) {
        return ApiResponse.success("Lấy role thành công", roleUseCase.getRoleById(id));
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.success("Lấy danh sách role thành công", roleUseCase.getAllRoles());
    }

    @PutMapping("/{id}")
    public ApiResponse<RoleResponse> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoleRequest request) {
        return ApiResponse.success("Cập nhật role thành công", roleUseCase.updateRole(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable Long id) {
        roleUseCase.deleteRole(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success("Xóa role thành công"));
    }
}