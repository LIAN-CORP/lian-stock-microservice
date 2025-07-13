package com.lian.marketing.lianstockmicroservice.domain.exception;

public class FileDownloadException extends RuntimeException {
    public FileDownloadException(String message) {
        super(message);
    }
}
