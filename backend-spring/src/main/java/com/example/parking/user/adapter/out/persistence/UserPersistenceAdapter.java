package com.example.parking.user.adapter.out.persistence;

import com.example.parking.auth.adapter.out.persistence.AuthUserEntity;
import com.example.parking.auth.adapter.out.persistence.IAuthUserJpaRepository;
import com.example.parking.auth.domain.model.UserRole;
import com.example.parking.user.application.port.out.IUserRepositoryPort;
import com.example.parking.user.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserPersistenceAdapter implements IUserRepositoryPort {

    private final IAuthUserJpaRepository jpaRepository;

    public UserPersistenceAdapter(IAuthUserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        AuthUserEntity entity = toEntity(user);
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    private User toDomain(AuthUserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPasswordHash(),
                entity.getRole(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone()
        );
    }

    private AuthUserEntity toEntity(User user) {
        return new AuthUserEntity(
                user.getId(),
                user.getUsername(),
                user.getPasswordHash(),
                user.getRole(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}