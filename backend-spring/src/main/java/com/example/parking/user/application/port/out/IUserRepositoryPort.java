package com.example.parking.user.application.port.out;

import com.example.parking.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepositoryPort {
    boolean existsByUsername(String username);
    Optional<User> findById(Long id);
    List<User> findAll();
    User save(User user);
    void deleteById(Long id);
    boolean existsById(Long id);
}