package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.ProductResponse;
import com.lian.marketing.lianstockmicroservice.application.mapper.IProductMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.IProductServicePort;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductHandler {

    private final IProductMapper productMapper;
    private final IProductServicePort productServicePort;

    public void createProduct(CreateProductRequest request) {
        productServicePort.createProduct(productMapper.toModel(request));
    }

    public ContentPage<ProductResponse> findAllProducts(int page, int size, boolean isAsc, String sortBy) {
        ContentPage<Product> contentPage = productServicePort.findAllProducts(page, size, isAsc, sortBy);
        List<ProductResponse> productResponseList = productMapper.toResponseList(contentPage.getContent());
        return new ContentPage<>(
                contentPage.getTotalPage(),
                contentPage.getTotalElements(),
                contentPage.getPageNumber(),
                contentPage.getPageSize(),
                contentPage.isFirst(),
                contentPage.isLast(),
                productResponseList
        );
    }

}
