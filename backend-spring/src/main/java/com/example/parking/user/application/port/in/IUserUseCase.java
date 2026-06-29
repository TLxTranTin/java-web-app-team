package com.example.parking.user.application.port.in;

import com.example.parking.user.dto.CreateUserRequest;
import com.example.parking.user.dto.UpdateUserRequest;
import com.example.parking.user.dto.UserResponse;

import java.util.List;

public interface IUserUseCase {
    UserResponse createUser(CreateUserRequest request);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UpdateUserRequest request);
    void deleteUser(Long id);
}