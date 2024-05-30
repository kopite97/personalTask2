package com.sparta.personaltask2.entity;

import com.sparta.personaltask2.dto.RequestScheduleDto;
import com.sparta.personaltask2.dto.RequestScheduleUpdateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false, length = 500)
    private String content;
    @Column(name = "worker", nullable = false)
    private String worker;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "schedule",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Schedule(RequestScheduleDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.worker = requestDto.getWorker();
        this.password =requestDto.getPassword();
    }

    public void update(RequestScheduleUpdateDto requestScheduleUpdateDto) {
        this.title = requestScheduleUpdateDto.getTitle();
        this.content = requestScheduleUpdateDto.getContent();
        this.worker = requestScheduleUpdateDto.getWorker();
    }
}
