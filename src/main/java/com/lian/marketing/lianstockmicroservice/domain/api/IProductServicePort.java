package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.Product;

public interface IProductServicePort {
    void createProduct(Product product);
}
