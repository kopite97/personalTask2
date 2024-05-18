package com.sparta.personaltask2.controller;

import com.sparta.personaltask2.entity.ErrorResponse;
import com.sparta.personaltask2.service.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ScheduleExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        HttpStatus httpStatusCode = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = createResponse(httpStatusCode,e.getMessage());
        return new ResponseEntity<>(errorResponse, httpStatusCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        HttpStatus httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = createResponse(httpStatusCode, e.getMessage());
        return new ResponseEntity<>(errorResponse, httpStatusCode);
    }


    private ErrorResponse createResponse(HttpStatus httpStatusCode,String message) {
        return new ErrorResponse(httpStatusCode.value(),httpStatusCode.name(),message);
    }
}

