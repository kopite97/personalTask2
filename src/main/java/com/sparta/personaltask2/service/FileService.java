package com.sparta.personaltask2.service;

import com.sparta.personaltask2.entity.FileEntity;
import com.sparta.personaltask2.repository.FileRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private static final String DIRECTORY = "C:\\";
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

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    public ResponseEntity<Resource> downloadFile(Long id) {
        FileEntity fileEntity = getFile(id);
        if (fileEntity != null) {
            byte[] fileData = fileEntity.getData();
            String fileName = fileEntity.getFileName();


            ByteArrayResource resource = new ByteArrayResource(fileData);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }
}
