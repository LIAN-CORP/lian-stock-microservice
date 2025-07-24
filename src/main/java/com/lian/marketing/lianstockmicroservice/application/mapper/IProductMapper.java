package com.lian.marketing.lianstockmicroservice.application.mapper;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.DiscountProductStockRequest;
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
    @Mapping(target = "subcategory", source = "subcategory.name")
    @Mapping(target = "category", source = "subcategory.category.name")
    ProductResponse toResponse(Product product);
    List<ProductResponse> toResponseList(List<Product> products);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "stock", source = "quantity")
    @Mapping(target = "priceSell",ignore = true)
    @Mapping(target = "priceBuy", ignore = true)
    @Mapping(target = "subcategory", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    Product toModelFromDiscountRequest(DiscountProductStockRequest request);

    List<Product> toModelListFromDiscountListRequest(List<DiscountProductStockRequest> request);
}
