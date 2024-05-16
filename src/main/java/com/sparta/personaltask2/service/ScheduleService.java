package com.sparta.personaltask2.service;

import com.sparta.personaltask2.dto.RequestScheduleDto;
import com.sparta.personaltask2.dto.ResponseScheduleDto;
import com.sparta.personaltask2.entity.Schedule;
import com.sparta.personaltask2.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    public ResponseScheduleDto createSchedule(RequestScheduleDto requestScheduleDto) {
        Schedule schedule = new Schedule(requestScheduleDto);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        scheduleRepository.flush();

        ResponseScheduleDto responseScheduleDto = new ResponseScheduleDto(schedule);

        return responseScheduleDto;
    }

    public List<ResponseScheduleDto> getSchedules() {
        return scheduleRepository.findAll().stream().map(ResponseScheduleDto::new).toList();
    }
}
