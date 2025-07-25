package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.controller;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.CategoryResponse;
import com.lian.marketing.lianstockmicroservice.application.handler.CategoryHandler;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.constants.OpenApiConstants;
import com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.exceptionhandler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryHandler categoryHandler;

    @Operation(summary = OpenApiConstants.CATEGORY_POST_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.CREATED_STATUS_CODE, description = OpenApiConstants.CATEGORY_DESCRIPTION_201,
                content = {
                    @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = CreateCategoryRequest.class)
            )}),
            @ApiResponse(responseCode = OpenApiConstants.BAD_REQUEST_STATUS_CODE, description = OpenApiConstants.CATEGORY_DESCRIPTION_400,
                content = {
                    @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = ExceptionResponse.class)
            )})
    })
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryHandler.saveCategory(request)
        );
    }

    @Operation(summary = OpenApiConstants.CATEGORIES_GET_ALL_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.CATEGORIES_GET_ALL_DESCRIPTION_201,
            content = {
                    @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = ContentPage.class))
            }),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.CATEGORY_DESCRIPTION_404,
            content = {
                    @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = ExceptionResponse.class))
            })
    })
    @GetMapping
    public ResponseEntity<ContentPage<CategoryResponse>> getAllCategories(
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_IS_ASC) boolean isAsc
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                categoryHandler.findAllCategories(page, size, isAsc)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(
                categoryHandler.findCategoryById(id)
        );
    }

}
