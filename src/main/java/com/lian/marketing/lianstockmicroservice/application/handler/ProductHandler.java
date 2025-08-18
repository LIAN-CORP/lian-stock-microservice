package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.DiscountProductStockRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.UpdateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.ProductFullResponse;
import com.lian.marketing.lianstockmicroservice.application.dto.response.ProductResponse;
import com.lian.marketing.lianstockmicroservice.application.mapper.IProductMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.IProductServicePort;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductHandler {

    private final IProductMapper productMapper;
    private final IProductServicePort productServicePort;

    public void createProduct(CreateProductRequest request, MultipartFile file) {
        productServicePort.createProduct(productMapper.toModel(request), file);
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

    public ContentPage<ProductResponse> findProductsByName(int page, int size, boolean isAsc, String sortBy, String name) {
        ContentPage<Product> contentPage = productServicePort.findProductsByName(page, size, isAsc, sortBy, name);
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

    public void discountProductsInStock(List<DiscountProductStockRequest> request) {
        productServicePort.discountProductsInStock(productMapper.toModelListFromDiscountListRequest(request));
    }

    public ProductFullResponse findProductById(UUID id) {
        return productMapper.toFullResponse(productServicePort.getProductById(id));
    }

    public void updateProduct(UpdateProductRequest request) {
        productServicePort.updateProduct(productMapper.toModelFromUpdateRequest(request));
    }

    public void deleteProductById(UUID id) {
        productServicePort.deleteProductById(id);
    }

    public Double getPriceSellByProductId(UUID id) {
        return productServicePort.getPriceSellByProductId(id);
    }

    public void addProductToStock(List<DiscountProductStockRequest> products) {
        productServicePort.addProductToStock(productMapper.toModelListFromDiscountListRequest(products));
    }

    public Double getPriceBuyByProductId(UUID id) {
        return productServicePort.getPriceBuyByProductId(id);
    }
}
