package com.sparta.personaltask2.dto;

import lombok.Getter;

@Getter
public class ResponseScheduleDeleteDto {
    private Long id;


    public ResponseScheduleDeleteDto(Long id) {
        this.id = id;
    }
}
