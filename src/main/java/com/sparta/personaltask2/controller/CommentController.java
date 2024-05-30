package com.sparta.personaltask2.controller;


import com.sparta.personaltask2.dto.commentDto.CommentRequestDto;
import com.sparta.personaltask2.dto.commentDto.CommentResponseDto;
import com.sparta.personaltask2.service.commentService.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{commentId}")
    public List<CommentResponseDto> getComments(@PathVariable Long commentId) {
        return commentService.getComments(commentId);
    }


    @PostMapping("/comment")
    public CommentResponseDto postComment(@RequestBody CommentRequestDto commentRequestDto) {

        return commentService.postComment(commentRequestDto);
    }

    @PutMapping("/comment/{commentId}")
    public CommentResponseDto putComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.putComment(commentId, commentRequestDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
