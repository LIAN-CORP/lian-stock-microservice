package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import com.lian.marketing.lianstockmicroservice.domain.spi.IProductPersistencePort;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.ProductEntity;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper.IProductEntityMapper;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository.IProductRepository;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.util.AdapterConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public ContentPage<Product> findAllProducts(int page, int size, boolean isAsc, String sortBy) {
        Sort sort = isAsc
                ? Sort.by(AdapterConstants.getValueSortMappingProducts(sortBy)).ascending()
                : Sort.by(AdapterConstants.getValueSortMappingProducts(sortBy)).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductEntity> products = productRepository.findAll(pageable);
        List<Product> productList = productEntityMapper.toProductList(products.getContent());
        return new ContentPage<>(
                products.getTotalPages(),
                products.getTotalElements(),
                products.getPageable().getPageNumber(),
                products.getPageable().getPageSize(),
                products.isFirst(),
                products.isLast(),
                productList
        );
    }

    @Override
    public boolean productExistsByUUID(UUID id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean isProductInStock(UUID id, Integer quantity) {
        return productRepository.isInStock(id, quantity);
    }

    @Override
    public boolean isPriceSellValid(UUID id, Double priceSell) {
        return productRepository.isPriceSellValid(id, priceSell);
    }

    @Override
    public void discountProductInStock(UUID id, Integer quantity) {
        productRepository.updateStock(id, quantity);
    }
}
