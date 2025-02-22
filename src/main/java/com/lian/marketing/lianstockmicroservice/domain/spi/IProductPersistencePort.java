package com.lian.marketing.lianstockmicroservice.domain.spi;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;

public interface IProductPersistencePort {
    void saveProduct(Product product);
    ContentPage<Product> findAllProducts(int page, int size, boolean isAsc, String sortBy);
}
