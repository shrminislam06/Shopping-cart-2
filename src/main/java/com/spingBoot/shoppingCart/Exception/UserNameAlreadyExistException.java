package com.spingBoot.shoppingCart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
@RestControllerAdvice
public class UserNameAlreadyExistException extends RuntimeException {
    @ExceptionHandler(value = UserNameAlreadyExistException.class)
    public ResponseEntity<ErrorApi> userExist() {
        ErrorApi error = new ErrorApi("BAD_REQUEST", "username already exist", new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
