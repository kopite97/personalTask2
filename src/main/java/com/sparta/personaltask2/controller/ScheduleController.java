package com.sparta.personaltask2.controller;

import com.sparta.personaltask2.dto.RequestScheduleDto;
import com.sparta.personaltask2.dto.ResponseScheduleDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @GetMapping("")
    public List<ResponseScheduleDto> getSchedule() {

        return null;
    }

    @PostMapping("")
    public ResponseScheduleDto createSchedule(@RequestBody RequestScheduleDto requestScheduleDto) {

        return null;
    }

    @DeleteMapping("{id}")
    public Long deleteSchedule(@PathVariable Long id) {
        return null;
    }

    @PutMapping("{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody RequestScheduleDto requestScheduleDto) {
        return null;
    }
}
