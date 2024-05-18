package com.sparta.personaltask2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int httpStatusCode;
    private String StatusMessage;
    private String message;
}
