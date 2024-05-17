package com.sparta.personaltask2.dto;

import com.sparta.personaltask2.entity.Schedule;
import lombok.Getter;

@Getter
public class ResponseShceduleUpdateDto {
    private String title;
    private String content;
    private String worker;

    public ResponseShceduleUpdateDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.worker = schedule.getWorker();
    }
}
