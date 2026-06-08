package com.example.parking.role.application.port.out;

import com.example.parking.role.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleRepositoryPort {
    boolean existsByName(String name);
    boolean existsById(Long id);
    Optional<Role> findById(Long id);
    List<Role> findAll();
    Role save(Role role);
    void deleteById(Long id);
}