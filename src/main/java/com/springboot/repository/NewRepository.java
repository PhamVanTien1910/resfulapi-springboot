package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {}
