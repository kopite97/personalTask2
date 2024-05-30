package com.sparta.personaltask2.service.commentService;

import com.sparta.personaltask2.dto.commentDto.CommentRequestDto;
import com.sparta.personaltask2.dto.commentDto.CommentResponseDto;
import com.sparta.personaltask2.entity.Comment;
import com.sparta.personaltask2.entity.Schedule;
import com.sparta.personaltask2.repository.CommentRepository;
import com.sparta.personaltask2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public List<CommentResponseDto> getComments(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정이 존재하지 않습니다")
        );
        System.out.println("scheduleId = " + scheduleId);

        List<Comment> commentList = schedule.getComments();
        System.out.println("commentList = " + commentList);
        for (Comment comment : commentList) {
            System.out.println("comment = " + comment.getContent());
        }
        return commentList.stream().map(CommentResponseDto::new).toList();
    }

    public CommentResponseDto postComment(CommentRequestDto commentRequestDto) {
        Schedule schedule = scheduleRepository.findById(commentRequestDto.getScheduleId()).orElseThrow(
                () -> new IllegalArgumentException("선택한 스케줄이 존재하지 않습니다")
        );

        System.out.println("commentRequestDto.getContent() = " + commentRequestDto.getContent());
        if(commentRequestDto.getContent() == null || commentRequestDto.getContent().isEmpty())
        {
            throw new IllegalArgumentException("댓글의 내용이 비어있습니다.");
        }

        Comment comment = new Comment(commentRequestDto,schedule);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto putComment(Long commentId,CommentRequestDto commentRequestDto) {
        Comment comment = getCommentById(commentId);
        if(commentRequestDto.getContent() == null || commentRequestDto.getContent().isBlank())
        {
            throw new IllegalArgumentException("댓글의 내용이 비어있습니다.");
        }

        comment.setContent(commentRequestDto.getContent());
        System.out.println("commentId = " + commentId);
        System.out.println("commentRequestDto.getContent() = " + commentRequestDto.getContent());
        System.out.println("commentRequestDto.getUserId() = " + commentRequestDto.getUserId());
        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity<String> deleteComment(Long commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.delete(comment);

        return ResponseEntity.ok("Comment deleted successfully");
    }

    private Comment getCommentById(Long commentId) {

        System.out.println("commentId = " + commentId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        return comment;
    }

}
