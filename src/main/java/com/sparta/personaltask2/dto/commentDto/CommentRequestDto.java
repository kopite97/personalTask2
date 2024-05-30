package com.sparta.personaltask2.dto.commentDto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long scheduleId;
    private String content;
    private String userId;
}
