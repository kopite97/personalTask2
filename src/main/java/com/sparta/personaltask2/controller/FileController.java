package com.sparta.personaltask2.controller;


import com.sparta.personaltask2.entity.FileEntity;
import com.sparta.personaltask2.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final String DIRECTORY = "C:\\";
    public final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> fileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            long fileSize = file.getSize();
            System.out.println("Uploaded file size: " + fileSize + " bytes");

            FileEntity fileEntity = fileService.storeFile(file);

            response.put("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            response.put("fileId", fileEntity.getId().toString());
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("message", "File upload failed.");
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/uploaded")
    public List<FileEntity> getUploadedFiles(){
        return fileService.getAllFiles();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        //Path filePath = Paths.get(DIRECTORY).resolve(fileName).normalize();
        return fileService.downloadFile(id);
    }
}
