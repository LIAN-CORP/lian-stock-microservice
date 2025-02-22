package com.lian.marketing.lianstockmicroservice.domain.api.usecase;

import com.lian.marketing.lianstockmicroservice.domain.api.IProductServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import com.lian.marketing.lianstockmicroservice.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductUseCase implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;
    private final ISubcategoryServicePort subcategoryServicePort;

    @Override
    public void createProduct(Product product) {
        subcategoryServicePort.subcategoryExistsByUUID(product.getSubcategory().getId());
        productPersistencePort.saveProduct(product);
    }
}
