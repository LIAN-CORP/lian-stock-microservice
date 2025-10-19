package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.controller;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.UpdateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryFullResponse;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryItemResponse;
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

import java.util.UUID;

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
    public ResponseEntity<SubcategoryItemResponse> createSubcategory(@Valid @RequestBody CreateSubcategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                subcategoryHandler.createSubcategory(request)
        );
    }

    @Operation(summary = OpenApiConstants.SUBCATEGORY_GET_ALL_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_GET_ALL_DESCRIPTION_200,
                content = {
                    @Content(schema = @Schema(implementation = ContentPage.class))
                }
            ),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.CATEGORY_DESCRIPTION_404,
                content = {
                    @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = ExceptionResponse.class))
                }
            )
    })
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
    @GetMapping("/parent/{id}")
    public ResponseEntity<ContentPage<SubcategoryItemResponse>> getAllSubcategoriesByCategory(
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_IS_ASC) boolean isAsc,
            @RequestParam(defaultValue = OpenApiConstants.DEFAULT_SORT_BY) String sortBy,
            @PathVariable("id") UUID categoryId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                subcategoryHandler.findAllSubcategoriesByCategory(page, size, isAsc, sortBy, categoryId)
        );
    }

    @Operation(summary = OpenApiConstants.SUBCATEGORY_GET_BY_ID_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_GET_ALL_DESCRIPTION_200,
                    content = {
                            @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = SubcategoryFullResponse.class))
                    }
            ),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryFullResponse> getSubcategory(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(
                subcategoryHandler.findSubcategoryById(id)
        );
    }

    @Operation(summary = OpenApiConstants.SUBCATEGORY_PUT_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_GET_ALL_DESCRIPTION_200),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @PutMapping
    public ResponseEntity<Void> putSubcategory(@Valid @RequestBody UpdateSubcategoryRequest request) {
        subcategoryHandler.updateCategory(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = OpenApiConstants.SUBCATEGORY_DELETE_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OK_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_DELETE_DESCRIPTION_204),
            @ApiResponse(responseCode = OpenApiConstants.NOT_FOUND_STATUS_CODE, description = OpenApiConstants.SUBCATEGORY_DESCRIPTION_404,
                    content = {
                            @Content(mediaType = OpenApiConstants.MEDIATYPE_JSON, schema = @Schema(implementation = ExceptionResponse.class))
                    }
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable("id") UUID id) {
        subcategoryHandler.deleteSubcategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
