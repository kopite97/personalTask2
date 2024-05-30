package com.sparta.personaltask2.dto.commentDto;

import com.sparta.personaltask2.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private String userId;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.commentId = comment.getId();
        this.userId = comment.getUserId();
    }
}
