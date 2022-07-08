package com.appwise.controller;

import com.appwise.exception.NoRecordFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<Object> exception(NoRecordFoundException ex) {
       return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
