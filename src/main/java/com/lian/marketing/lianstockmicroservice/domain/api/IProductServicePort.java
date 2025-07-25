package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IProductServicePort {
    void createProduct(Product product, MultipartFile file);
    ContentPage<Product> findAllProducts(int page, int size, boolean isAsc, String sortBy);
    void discountProductsInStock(List<Product> product);
    Product getProductById(UUID id);
    void updateProduct(Product product);
}
