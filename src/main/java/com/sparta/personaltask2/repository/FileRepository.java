package com.sparta.personaltask2.repository;

import com.sparta.personaltask2.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
