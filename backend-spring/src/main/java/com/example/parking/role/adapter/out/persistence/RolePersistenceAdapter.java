package com.example.parking.role.adapter.out.persistence;

import com.example.parking.role.application.port.out.IRoleRepositoryPort;
import com.example.parking.role.domain.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RolePersistenceAdapter implements IRoleRepositoryPort {

    private final IRoleJpaRepository jpaRepository;

    public RolePersistenceAdapter(IRoleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Role save(Role role) {
        RoleEntity entity = new RoleEntity(role.getId(), role.getName(), role.getDescription());
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    private Role toDomain(RoleEntity entity) {
        return new Role(entity.getId(), entity.getName(), entity.getDescription());
    }
}