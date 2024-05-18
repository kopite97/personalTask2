package com.sparta.personaltask2.controller;


import com.sparta.personaltask2.entity.FileEntity;
import com.sparta.personaltask2.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {

    public final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> fileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            FileEntity fileEntity = fileService.storeFile(file);
            response.put("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            response.put("fileId", fileEntity.getId().toString());
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("message", "File upload failed.");
            return ResponseEntity.status(500).body(response);
        }
    }

}
