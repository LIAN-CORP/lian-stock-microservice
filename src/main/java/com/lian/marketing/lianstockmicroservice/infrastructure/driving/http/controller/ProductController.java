package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.controller;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.DiscountProductStockRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.UpdateProductRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.ProductFullResponse;
import com.lian.marketing.lianstockmicroservice.application.dto.response.ProductResponse;
import com.lian.marketing.lianstockmicroservice.application.handler.ProductHandler;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.constants.OpenApiConstants;
import com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.exceptionhandler.ExceptionResponse;
import com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.swagger.CreateProductMultiPartRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductHandler productHandler;

    @Operation(summary = OpenApiConstants.PRODUCT_POST_SUMMARY,
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                content = {
                        @Content(
                                mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                schema = @Schema(implementation = CreateProductMultiPartRequest.class)
                        )
                }
        )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.CREATED_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_201,
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))
                }
            ),
            @ApiResponse(responseCode = OpenApiConstants.BAD_REQUEST_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_400,
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
                }
            )
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(
            @RequestPart(value = "product", required = true) @Valid CreateProductRequest request,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        productHandler.createProduct(request, imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = OpenApiConstants.PRODUCT_GET_ALL_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.PRODUCT_GET_ALL_DESCRIPTION_200,
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContentPage.class))
                }
            ),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_404,
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
                }
            )
    })
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

    @PostMapping("/discount")
    public ResponseEntity<Void> discountProducts(@Valid @RequestBody List<DiscountProductStockRequest> request){
        productHandler.discountProductsInStock(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = OpenApiConstants.PRODUCT_GET_BY_ID_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.PRODUCT_GET_ALL_DESCRIPTION_200,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductFullResponse.class))
                    }
            ),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductFullResponse> getProductById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                productHandler.findProductById(id)
        );
    }

    @Operation(summary = OpenApiConstants.PRODUCT_PUT_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.PRODUCT_GET_ALL_DESCRIPTION_200),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @PutMapping
    public ResponseEntity<Void> putProduct(@Valid @RequestBody UpdateProductRequest request) {
        productHandler.updateProduct(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = OpenApiConstants.PRODUCT_DELETE_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.PRODUCT_DELETE_DESCRIPTION_204),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID id) {
        productHandler.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = OpenApiConstants.PRODUCT_GET_PRICE_BY_ID_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.PRODUCT_GET_PRICE_BY_ID_DESCRIPTION_200,
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Double.class))
                }
            ),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @GetMapping("/price/{id}")
    public ResponseEntity<Double> getPriceSellProductById(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(
                productHandler.getPriceSellByProductId(id)
        );
    }

    @Operation(summary = OpenApiConstants.PRODUCT_POST_ADD_STOCK_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.PRODUCT_POST_ADD_STOCK_DESCRIPTION_200),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @PostMapping("/stock/add")
    public ResponseEntity<Void> postStockAddProductById(@Valid @RequestBody List<DiscountProductStockRequest> request){
        productHandler.addProductToStock(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = OpenApiConstants.PRODUCT_GET_PRICE_BUY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.PRODUCT_GET_PRICE_BUY_DESCRIPTION_200,
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Double.class))
                }
            ),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.PRODUCT_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @GetMapping("/price/buy/{id}")
    public ResponseEntity<Double> getPriceBuyProductById(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(
                productHandler.getPriceBuyByProductId(id)
        );
    }
}
