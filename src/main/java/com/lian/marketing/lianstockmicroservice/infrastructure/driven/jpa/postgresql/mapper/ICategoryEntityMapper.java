package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    CategoryEntity fromModelToCategoryEntity(Category category);
}
