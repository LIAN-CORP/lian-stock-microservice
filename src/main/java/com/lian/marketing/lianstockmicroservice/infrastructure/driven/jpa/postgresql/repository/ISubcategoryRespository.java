package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository;

import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.SubcategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISubcategoryRespository extends JpaRepository<SubcategoryEntity, UUID> {
    boolean existsByName(String name);
    boolean existsById(UUID id);
    Page<SubcategoryEntity> findAllByCategory_Id(UUID categoryId, Pageable pageable);
}
