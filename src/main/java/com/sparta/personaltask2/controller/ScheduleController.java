package com.sparta.personaltask2.controller;

import com.sparta.personaltask2.dto.*;
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

    @GetMapping("/schedules/{id}")
    public ResponseScheduleDto getScheduleById(@PathVariable int id) {
        return scheduleService.getScheduleById((long)id);
    }

    @PostMapping("/schedules")
    public ResponseScheduleDto createSchedule(@RequestBody RequestScheduleDto requestScheduleDto) {

        return scheduleService.createSchedule(requestScheduleDto);
    }

    @DeleteMapping("/schedules")
    public ResponseScheduleDeleteDto deleteSchedule(@RequestBody RequestScheduleDeleteDto requestScheduleDeleteDto) {

        return scheduleService.deleteSchedule(requestScheduleDeleteDto);
    }

    @PutMapping("/schedules")
    public ResponseShceduleUpdateDto updateShcedules(@RequestBody RequestScheduleUpdateDto requestScheduleUpdateDto) {
        return scheduleService.updateSchedule(requestScheduleUpdateDto);
    }

}
