package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.controller;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.ProductResponse;
import com.lian.marketing.lianstockmicroservice.application.handler.ProductHandler;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.constants.OpenApiConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductHandler productHandler;

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequest request) {
        productHandler.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ContentPage<ProductResponse>> getProducts(
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_IS_ASC) boolean isAsc,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_SORT_BY) String sortBy
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
          productHandler.findAllProducts(page, size, isAsc, sortBy)
        );
    }

}
