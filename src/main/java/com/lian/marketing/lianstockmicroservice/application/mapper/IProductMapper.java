package com.lian.marketing.lianstockmicroservice.application.mapper;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.ProductResponse;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ISubcategoryMapper.class})
public interface IProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategory.id", source = "subcategoryId")
    Product toModel(CreateProductRequest request);
    ProductResponse toResponse(Product product);
    List<ProductResponse> toResponseList(List<Product> products);
}
