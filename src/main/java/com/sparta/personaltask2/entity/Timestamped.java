package com.sparta.personaltask2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    @CreatedDate // 처음 생성될 때 자동으로 저장
    @Column(updatable = false) // Update가 되지 않게끔 막아 줌
    @Temporal(TemporalType.TIMESTAMP) // Java의 Date패키지에 들어있는 타입을 매핑할 때 사용
    private LocalDateTime createdAt;

    @LastModifiedDate //조회한 entity객체의 값을 변경할 때 변경된 시간이 자동으로 저장 (처음 생성 될 때도)
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}