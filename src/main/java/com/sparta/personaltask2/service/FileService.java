package com.sparta.personaltask2.service;

import com.sparta.personaltask2.entity.FileEntity;
import com.sparta.personaltask2.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;
    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public FileEntity storeFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setData(file.getBytes());

        return fileRepository.save(fileEntity);
    }

    public FileEntity getFile(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 파일은 존재하지 않습니다. (File ID : "+id));
    }
}
