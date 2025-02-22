package com.lian.marketing.lianstockmicroservice.application.mapper;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ISubcategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", expression = "java(UUID.fromString(request.categoryId()))")
    Subcategory toModelFromRequest(CreateSubcategoryRequest request);
}
