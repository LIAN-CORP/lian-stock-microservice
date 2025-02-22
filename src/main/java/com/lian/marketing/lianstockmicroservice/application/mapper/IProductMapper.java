package com.lian.marketing.lianstockmicroservice.application.mapper;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategory.id", source = "subcategoryId")
    Product toModel(CreateProductRequest request);
}
