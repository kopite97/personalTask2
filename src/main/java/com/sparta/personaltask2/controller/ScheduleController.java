package com.sparta.personaltask2.controller;

import com.sparta.personaltask2.dto.RequestScheduleDto;
import com.sparta.personaltask2.dto.ResponseScheduleDto;
import com.sparta.personaltask2.entity.Schedule;
import com.sparta.personaltask2.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedules")
    public List<ResponseScheduleDto> getSchedule() {

        return scheduleService.getSchedules();
    }

    @PostMapping("/schedules")
    public ResponseScheduleDto createSchedule(@RequestBody RequestScheduleDto requestScheduleDto) {

        return scheduleService.createSchedule(requestScheduleDto);
    }

    @DeleteMapping("/schedules")
    public Long deleteSchedule(@RequestParam Long id, @RequestParam String password) {

        return scheduleService.deleteSchedule(id,password);
    }

    @PutMapping("/schedules/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody RequestScheduleDto requestScheduleDto) {
        return scheduleService.updateSchedule(id, requestScheduleDto);
    }
}
