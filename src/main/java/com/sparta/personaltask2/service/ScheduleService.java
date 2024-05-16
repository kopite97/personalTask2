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

        ResponseScheduleDto responseScheduleDto = new ResponseScheduleDto(schedule);

        return responseScheduleDto;
    }

    public List<ResponseScheduleDto> getSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ResponseScheduleDto::new).toList();
    }

    public Long deleteSchedule(Long id,String pw) {
        Schedule schedule = findSchedule(id);
        if(!isEqualsPassword(pw, schedule.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다");
            return -1L;
        }

        scheduleRepository.delete(schedule);
        return id;
    }

    @Transactional
    public Long updateSchedule(Long id, RequestScheduleDto requestScheduleDto) {
        Schedule schedule = findSchedule(id);
        if(!isEqualsPassword(requestScheduleDto.getPassword(), schedule.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다");
            return -1L;
        }

        schedule.update(requestScheduleDto);

        return id;
    }
    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));
    }

    private boolean isEqualsPassword(String pw1, String pw2) {
        return pw1.equals(pw2);
    }


}
