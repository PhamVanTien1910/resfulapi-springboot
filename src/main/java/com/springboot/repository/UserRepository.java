package com.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUserName(String username);

    Optional<UserEntity> findByUserName(String username);
}
