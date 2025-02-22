package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.mapper.IProductMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.IProductServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductHandler {

    private final IProductMapper productMapper;
    private final IProductServicePort productServicePort;

    public void createProduct(CreateProductRequest request) {
        productServicePort.createProduct(productMapper.toModel(request));
    }

}
