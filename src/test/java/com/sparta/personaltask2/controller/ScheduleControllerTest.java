package com.sparta.personaltask2.controller;

import com.sparta.personaltask2.dto.RequestScheduleDto;
import com.sparta.personaltask2.entity.Schedule;
import com.sparta.personaltask2.repository.ScheduleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ScheduleControllerTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("게시")
    void test1(){

        RequestScheduleDto requestDto = new RequestScheduleDto();
        requestDto.setTitle("Test Title");
        requestDto.setContent("Test Content");
        requestDto.setWorker("Test Worker");

        // when
        Schedule savedSchedule = scheduleRepository.save(new Schedule(requestDto));

        // then
        assertNotNull(savedSchedule.getId()); // 저장된 Schedule 엔티티의 ID가 null이 아닌지 확인
        assertEquals("Test Title", savedSchedule.getTitle()); // 저장된 Schedule 엔티티의 Title이 예상한 값과 일치하는지 확인
        assertEquals("Test Content", savedSchedule.getContent()); // 저장된 Schedule 엔티티의 Content가 예상한 값과 일치하는지 확인
        assertEquals("Test Worker", savedSchedule.getWorker()); // 저장된 Schedule 엔티티의 Worker가 예상한 값과 일치하는지 확인

    }


}