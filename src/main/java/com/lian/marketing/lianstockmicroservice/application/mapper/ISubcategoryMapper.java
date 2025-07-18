package com.lian.marketing.lianstockmicroservice.application.mapper;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryItemResponse;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryResponse;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ICategoryMapper.class})
public interface ISubcategoryMapper {
    @Mapping(target = "id", ignore = true)
    //@Mapping(target = "categoryId", expression = "java(UUID.fromString(request.categoryId()))")
    @Mapping(target = "category.id", source = "categoryId")
    Subcategory toModelFromRequest(CreateSubcategoryRequest request);
    SubcategoryResponse fromModelToResponse(Subcategory subcategory);
    List<SubcategoryResponse> toResponseModelListFromModelList(List<Subcategory> subcategories);
    List<SubcategoryItemResponse> toItemResponseModelListFromModelList(List<Subcategory> subcategories);
}
