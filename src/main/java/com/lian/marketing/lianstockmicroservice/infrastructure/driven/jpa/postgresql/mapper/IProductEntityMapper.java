package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper;

import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ISubcategoryEntityMapper.class})
public interface IProductEntityMapper {
    ProductEntity toEntity(Product product);
    Product toProduct(ProductEntity productEntity);
    List<Product> toProductList(List<ProductEntity> productEntityList);
}
