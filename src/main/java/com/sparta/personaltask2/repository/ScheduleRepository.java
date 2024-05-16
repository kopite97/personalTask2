package com.sparta.personaltask2.repository;

import com.sparta.personaltask2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
