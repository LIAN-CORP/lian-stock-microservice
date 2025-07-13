package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository;

import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<ProductEntity, UUID> {
}
