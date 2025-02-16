package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.controller;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.handler.CategoryHandler;
import com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.constants.OpenApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryHandler categoryHandler;

    @Operation(summary = OpenApiConstants.CATEGORY_POST_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.CREATED_STATUS_CODE, description = OpenApiConstants.CATEGORY_DESCRIPTION_200,
                content = {
                    @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = CreateCategoryRequest.class)
            )})
    })
    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        categoryHandler.saveCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
