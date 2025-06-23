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
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(FileDownloadException.class)
    public ResponseEntity<ExceptionResponse> handleFileDownloadException(FileDownloadException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ExceptionResponse(
                HttpStatus.BAD_GATEWAY.toString(),
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ExceptionResponse> handleFileUploadException(FileUploadException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ExceptionResponse(
                HttpStatus.BAD_GATEWAY.toString(),
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(SdkAwsException.class)
    public ResponseEntity<ExceptionResponse> handleSdkAwsException(SdkAwsException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ExceptionResponse(
                HttpStatus.BAD_GATEWAY.toString(),
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                LocalDateTime.now()
        ));
    }
}