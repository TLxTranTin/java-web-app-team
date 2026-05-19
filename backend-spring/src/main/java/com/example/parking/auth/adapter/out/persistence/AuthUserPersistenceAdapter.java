package com.example.parking.auth.adapter.out.persistence;

import com.example.parking.auth.application.port.out.IAuthUserRepositoryPort;
import com.example.parking.auth.domain.model.AuthUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthUserPersistenceAdapter implements IAuthUserRepositoryPort {

    private final AuthUserJpaRepository jpaRepository;

    public AuthUserPersistenceAdapter(AuthUserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public Optional<AuthUser> findByUsername(String username) {
        return jpaRepository.findByUsername(username)
                .map(this::toDomain);
    }

    @Override
    public AuthUser save(AuthUser user) {
        AuthUserEntity entity = toEntity(user);
        AuthUserEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    private AuthUser toDomain(AuthUserEntity entity) {
        return new AuthUser(
                entity.getId(),
                entity.getUsername(),
                entity.getPasswordHash(),
                entity.getRole()
        );
    }

    private AuthUserEntity toEntity(AuthUser user) {
        return new AuthUserEntity(
                user.getId(),
                user.getUsername(),
                user.getPasswordHash(),
                user.getRole()
        );
    }
}