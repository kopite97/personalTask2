package com.sparta.personaltask2.repository;

import com.sparta.personaltask2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByScheduleId(Long schduleId);
}
