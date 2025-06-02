package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.lian.marketing.lianstockmicroservice.domain.constants.CoreConstants;
import com.lian.marketing.lianstockmicroservice.domain.constants.ExceptionConstants;
import com.lian.marketing.lianstockmicroservice.domain.exception.AmazonS3Exception;
import com.lian.marketing.lianstockmicroservice.domain.exception.FileDownloadException;
import com.lian.marketing.lianstockmicroservice.domain.exception.FileUploadException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SdkAwsException;
import com.lian.marketing.lianstockmicroservice.domain.spi.IS3PersistencePort;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class S3Adapter implements IS3PersistencePort {

    private final AmazonS3 s3client;

    @Override
    public String uploadFile(String bucketName, String keyName, Long contentLength, String contentType, InputStream inputStream) {
        try{
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            metadata.setContentType(contentType);
            s3client.putObject(bucketName, keyName, inputStream, metadata);
            return String.format(CoreConstants.AWS_S3_BASE_PATH, bucketName, keyName);
        } catch (AmazonServiceException e) {
            throw new FileUploadException(String.format(ExceptionConstants.FILE_UPLOAD_FAILED, e.getMessage()));
        } catch (SdkClientException e) {
            throw new SdkAwsException(String.format(ExceptionConstants.SDK_AMAZON_EXCEPTION, e.getMessage()));
        }
    }

    @Override
    public ByteArrayOutputStream downloadFile(String bucketName, String keyName) {
        try{
            S3Object s3object = s3client.getObject(bucketName, keyName);
            InputStream inputStream = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while((len = inputStream.read(buffer, 0, buffer.length)) != -1){
                outputStream.write(buffer, 0, len);
            }
            return outputStream;

        } catch (IOException e) {
            throw new FileDownloadException(String.format(ExceptionConstants.FILE_DOWNLOAD_FAILED, e.getMessage()));
        } catch (AmazonClientException e) {
            throw new AmazonS3Exception(String.format(ExceptionConstants.AMAZON_S3_CLIENT_FAILED, e.getMessage()));
        }
    }

    @Override
    public void deleteFile(String bucketName, String keyName) {
        try{
            s3client.deleteObject(bucketName, keyName);
        } catch (AmazonClientException e) {
            throw new AmazonS3Exception(String.format(ExceptionConstants.AMAZON_S3_CLIENT_FAILED, e.getMessage()));
        }
    }
}
