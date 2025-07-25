package com.lian.marketing.lianstockmicroservice.domain.spi;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface IProductPersistencePort {
    void saveProduct(Product product);
    ContentPage<Product> findAllProducts(int page, int size, boolean isAsc, String sortBy);
    boolean productExistsByUUID(UUID id);
    boolean isProductInStock(UUID id, Integer quantity);
    boolean isPriceSellValid(UUID id, Double priceSell);
    void discountProductInStock(UUID id, Integer quantity);
    Optional<Product> findProductById(UUID id);
    void updateProduct(Product product);
}
