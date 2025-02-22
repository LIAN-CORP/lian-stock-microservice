package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.controller;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.handler.SubcategoryHandler;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
