package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductServicePort {
    void createProduct(Product product, MultipartFile file);
    ContentPage<Product> findAllProducts(int page, int size, boolean isAsc, String sortBy);
    void discountProductsInStock(List<Product> product);
}
