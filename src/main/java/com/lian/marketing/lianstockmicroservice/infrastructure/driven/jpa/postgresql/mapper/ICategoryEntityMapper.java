package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    CategoryEntity fromModelToCategoryEntity(Category category);
    Category fromEntityToCategoryModel(CategoryEntity categoryEntity);
    List<Category> fromCategoryEntityToCategoryList(List<CategoryEntity> categoryEntities);
}
