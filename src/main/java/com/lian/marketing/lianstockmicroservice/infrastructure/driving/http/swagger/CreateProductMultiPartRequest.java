package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.swagger;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateProductRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

public class CreateProductMultiPartRequest {
    @Schema(description = "Product data in JSON format", required = true)
    private CreateProductRequest createProductRequest;

    @Schema(description = "Product image", type = "string", format = "binary")
    private MultipartFile image;

    public CreateProductRequest getCreateProductRequest() {
        return createProductRequest;
    }

    public void setCreateProductRequest(CreateProductRequest createProductRequest) {
        this.createProductRequest = createProductRequest;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
