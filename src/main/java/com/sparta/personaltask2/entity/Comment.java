package com.sparta.personaltask2.entity;

import com.sparta.personaltask2.dto.commentDto.CommentRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String content;
    private String userId;

    @ManyToOne
    @JoinColumn(name ="schedule_id",nullable = false)
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto,Schedule schedule) {
        this.content = requestDto.getContent();
        this.userId = requestDto.getUserId();
        this.schedule = schedule;
    }

}
