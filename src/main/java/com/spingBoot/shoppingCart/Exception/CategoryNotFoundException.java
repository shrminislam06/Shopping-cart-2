package com.spingBoot.shoppingCart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
@RestControllerAdvice

public class CategoryNotFoundException extends RuntimeException {

    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<ErrorApi> categoryNotFound() {
        ErrorApi error = new ErrorApi();
        error.setError("NOT_FOUND");
        error.setDescription("Category id not found");
        error.setDate(new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
