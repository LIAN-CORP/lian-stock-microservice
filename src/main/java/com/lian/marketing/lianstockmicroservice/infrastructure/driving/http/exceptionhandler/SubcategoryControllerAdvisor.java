package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.exceptionhandler;

import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoriesNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryAlreadyExistsException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class SubcategoryControllerAdvisor {
    @ExceptionHandler(SubcategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleSubcategoryAlreadyExistsException(SubcategoryAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(SubcategoriesNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSubcategoriesNotFoundException(SubcategoriesNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(SubcategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSubcategoryNotFoundException(SubcategoryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        ));
    }
}
