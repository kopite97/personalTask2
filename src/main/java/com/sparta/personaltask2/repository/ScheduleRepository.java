package com.sparta.personaltask2.repository;

import com.sparta.personaltask2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findAllByOrderByCreatedAtDesc();
}
