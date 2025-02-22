package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.controller;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryResponse;
import com.lian.marketing.lianstockmicroservice.application.handler.SubcategoryHandler;
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

@RestController
@RequestMapping("/subcategory")
@RequiredArgsConstructor
public class SubcategoryController {
    private final SubcategoryHandler subcategoryHandler;

    @Operation(summary = OpenApiConstants.SUBCATEGORY_POST_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.CREATED_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_DESCRIPTION_201,
            content = {
                    @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = CreateSubcategoryRequest.class))
            }),
            @ApiResponse(responseCode = OpenApiConstants.BAD_REQUEST_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_DESCRIPTION_400,
            content = {
                    @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = ExceptionResponse.class))
            })

    })
    @PostMapping
    public ResponseEntity<Void> createSubcategory(@Valid @RequestBody CreateSubcategoryRequest request){
        subcategoryHandler.createSubcategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ContentPage<SubcategoryResponse>> getAllSubcategories(
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_IS_ASC) boolean isAsc,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_SORT_BY) String sortBy
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                subcategoryHandler.findAllSubcategories(page, size, isAsc, sortBy)
        );
    }
}
