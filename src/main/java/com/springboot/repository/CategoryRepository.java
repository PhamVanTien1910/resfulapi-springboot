package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findOneByCode(String code);
}
