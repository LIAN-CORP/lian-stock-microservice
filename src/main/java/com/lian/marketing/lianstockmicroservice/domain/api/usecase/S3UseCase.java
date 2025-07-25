package com.lian.marketing.lianstockmicroservice.domain.api.usecase;

import com.lian.marketing.lianstockmicroservice.domain.api.IS3ServicePort;
import com.lian.marketing.lianstockmicroservice.domain.constants.CoreConstants;
import com.lian.marketing.lianstockmicroservice.domain.constants.ExceptionConstants;
import com.lian.marketing.lianstockmicroservice.domain.exception.FileUploadException;
import com.lian.marketing.lianstockmicroservice.domain.spi.IS3PersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
public class S3UseCase implements IS3ServicePort {

    private final IS3PersistencePort s3PersistencePort;

    @Override
    public String saveImage(MultipartFile file) {
        try {
            String keyName = UUID.randomUUID() + CoreConstants.SEPARATOR_NAME + file.getOriginalFilename();
            return s3PersistencePort.uploadFile(
                    CoreConstants.BUCKET_PRODUCT_NAME,
                    keyName,
                    file.getSize(),
                    file.getContentType(),
                    file.getInputStream()
            );

        } catch (IOException e) {
            throw new FileUploadException(String.format(ExceptionConstants.FILE_UPLOAD_FAILED, e.getMessage()));
        }
    }

}
