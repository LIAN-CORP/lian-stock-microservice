package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;

public interface IProductServicePort {
    void createProduct(Product product);
    ContentPage<Product> findAllProducts(int page, int size, boolean isAsc, String sortBy);
}
