package com.spingBoot.shoppingCart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
@RestControllerAdvice
public class ResourceNotFoundException extends Exception {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorApi> resourceNotFound() {
        ErrorApi error = new ErrorApi("BAD_REQUEST", "Resource not found", new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
