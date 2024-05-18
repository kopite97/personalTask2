package com.sparta.personaltask2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "file_Storage")
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_name",nullable = false)
    private String fileName;
    @Column(name = "file_type",nullable = false)
    private String fileType;

    @Lob
    @Column(name = "data",nullable = false)
    private byte[] data;

}
