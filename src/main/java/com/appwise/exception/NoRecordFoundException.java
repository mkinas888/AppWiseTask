package com.appwise.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class NoRecordFoundException extends RuntimeException {

    private NoRecordFoundException() {
        timestamp = LocalDateTime.now();
    }

    public NoRecordFoundException(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    @Getter
    private HttpStatus status;

    @Getter
    private String message;

    @Getter
    private final LocalDateTime timestamp;
}
