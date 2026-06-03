package com.uditray.enterprise.service;

import com.uditray.enterprise.entity.*;
import com.uditray.enterprise.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.uditray.enterprise.exception.ResourceNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final TaskRepository taskRepository;
    private final FileAttachmentRepository fileRepository;

    private final String uploadDir =
            System.getProperty("user.dir")
                    + "/uploads/";

    public FileAttachment uploadFile(
            Long taskId,
            MultipartFile file
    ) throws IOException {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"
                        )
                );

        String uniqueFileName =
                UUID.randomUUID()
                        + "_"
                        + file.getOriginalFilename();

        File uploadFolder = new File(uploadDir);

        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        File destination =
                new File(uploadFolder, uniqueFileName);

        file.transferTo(destination);

        FileAttachment attachment =
                FileAttachment.builder()
                        .fileName(
                                file.getOriginalFilename()
                        )
                        .filePath(destination.getAbsolutePath())
                        .task(task)
                        .build();

        return fileRepository.save(attachment);
    }
}