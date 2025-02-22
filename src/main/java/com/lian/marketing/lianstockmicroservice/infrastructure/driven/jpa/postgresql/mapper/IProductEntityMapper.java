package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper;

import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ISubcategoryEntityMapper.class, ICategoryEntityMapper.class})
public interface IProductEntityMapper {
    ProductEntity toEntity(Product product);
}
