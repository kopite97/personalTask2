package com.sparta.personaltask2.dto;

import com.sparta.personaltask2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseScheduleDto {
    private Long id;
    private String title;
    private String content;
    private String worker;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ResponseScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.worker = schedule.getWorker();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
