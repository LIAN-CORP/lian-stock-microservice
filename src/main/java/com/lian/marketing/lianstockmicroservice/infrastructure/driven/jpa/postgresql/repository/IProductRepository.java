package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository;

import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsById(UUID id);

    @Query(value = "SELECT CASE WHEN p.stock >= :quantity THEN true ELSE false END FROM product p WHERE p.id = :id", nativeQuery = true)
    boolean isInStock(@Param("id") UUID id, @Param("quantity") Integer quantity);

    @Query(value = "SELECT CASE WHEN p.price_sell = :priceSell THEN true ELSE false END FROM product p WHERE p.id = :id", nativeQuery = true)
    boolean isPriceSellValid(@Param("id") UUID id, @Param("priceSell") Double priceSell);

    @Modifying
    @Query(value = "UPDATE product SET stock = stock - :quantity WHERE id = :id", nativeQuery = true)
    void updateStock(@Param("id") UUID id, @Param("quantity") Integer quantity);
}
