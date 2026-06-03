package com.uditray.enterprise.controller;

import com.uditray.enterprise.entity.FileAttachment;
import com.uditray.enterprise.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/upload/{taskId}")
    public FileAttachment upload(
            @PathVariable Long taskId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        return fileUploadService.uploadFile(
                taskId,
                file
        );
    }
}