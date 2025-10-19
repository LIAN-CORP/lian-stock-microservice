package com.lian.marketing.lianstockmicroservice.domain.api;

import org.springframework.web.multipart.MultipartFile;

public interface IS3ServicePort {
    String saveImage(MultipartFile file);
    void deleteImage(String imageName);
}
