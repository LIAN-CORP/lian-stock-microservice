package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository;

import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    boolean existsByName(String name);
    boolean existsById(UUID id);
}