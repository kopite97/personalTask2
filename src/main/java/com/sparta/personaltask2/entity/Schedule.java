package com.sparta.personaltask2.entity;

import com.sparta.personaltask2.dto.RequestScheduleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false, length = 500)
    private String content;
    @Column(name = "worker", nullable = false)
    private String worker;
    @Column(name = "password", nullable = false)
    private String password;

    public Schedule(RequestScheduleDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.worker = requestDto.getWorker();
        this.password ="1234";
    }


}
