package com.sparta.personaltask2.dto;

import lombok.Getter;

@Getter
public class RequestScheduleUpdateDto {
    private Long id;
    private String title;
    private String content;
    private String worker;
    private String password;
}
