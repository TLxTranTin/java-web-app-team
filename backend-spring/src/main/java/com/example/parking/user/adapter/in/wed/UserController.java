package com.example.parking.user.adapter.in.wed;

import com.example.parking.shared.response.ApiResponse;
import com.example.parking.user.application.port.in.IUserUseCase;
import com.example.parking.user.dto.CreateUserRequest;
import com.example.parking.user.dto.UpdateUserRequest;
import com.example.parking.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserUseCase userUseCase;

    public UserController(IUserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody CreateUserRequest request) {
        UserResponse response = userUseCase.createUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo user thành công", response));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        return ApiResponse.success("Lấy user thành công", userUseCase.getUserById(id));
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.success("Lấy danh sách user thành công", userUseCase.getAllUsers());
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        return ApiResponse.success("Cập nhật user thành công", userUseCase.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userUseCase.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success("Xóa user thành công"));
    }
}