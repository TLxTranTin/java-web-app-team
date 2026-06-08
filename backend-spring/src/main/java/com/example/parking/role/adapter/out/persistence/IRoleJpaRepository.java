package com.example.parking.role.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleJpaRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByName(String name);
}