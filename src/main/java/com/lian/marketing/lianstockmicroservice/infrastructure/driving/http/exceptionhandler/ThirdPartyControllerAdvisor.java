package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.exceptionhandler;

import com.lian.marketing.lianstockmicroservice.domain.exception.AmazonS3Exception;
import com.lian.marketing.lianstockmicroservice.domain.exception.FileDownloadException;
import com.lian.marketing.lianstockmicroservice.domain.exception.FileUploadException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SdkAwsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ThirdPartyControllerAdvisor {
    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<ExceptionResponse> handleAmazonS3Exception(AmazonS3Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ExceptionResponse(
                HttpStatus.BAD_GATEWAY.toString(),
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "AMAZON_S3_EXCEPTION"
        ));
    }

    @ExceptionHandler(FileDownloadException.class)
    public ResponseEntity<ExceptionResponse> handleFileDownloadException(FileDownloadException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ExceptionResponse(
                HttpStatus.BAD_GATEWAY.toString(),
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "FILE_DOWNLOAD_EXCEPTION"
        ));
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ExceptionResponse> handleFileUploadException(FileUploadException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ExceptionResponse(
                HttpStatus.BAD_GATEWAY.toString(),
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "FILE_UPLOAD_EXCEPTION"
        ));
    }

    @ExceptionHandler(SdkAwsException.class)
    public ResponseEntity<ExceptionResponse> handleSdkAwsException(SdkAwsException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ExceptionResponse(
                HttpStatus.BAD_GATEWAY.toString(),
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "SDK_AWS_EXCEPTION"
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "ILLEGAL_ARGUMENT_EXCEPTION"
        ));
    }
}