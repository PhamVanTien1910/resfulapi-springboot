package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByName(String userRole);
}
