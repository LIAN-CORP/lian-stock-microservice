package com.lian.marketing.lianstockmicroservice.domain.exception;

public class AmazonS3Exception extends RuntimeException {
    public AmazonS3Exception(String message) {
        super(message);
    }
}
