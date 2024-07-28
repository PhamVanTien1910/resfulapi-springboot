package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.InvalidatedTokenEntity;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedTokenEntity, String> {}
