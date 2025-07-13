package com.lian.marketing.lianstockmicroservice.domain.spi;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public interface IS3PersistencePort {
    String uploadFile(String bucketName, String keyName, Long contentLength, String contentType, InputStream inputStream);

    ByteArrayOutputStream downloadFile(String bucketName, String keyName);

    void deleteFile(String bucketName, String keyName);
}
