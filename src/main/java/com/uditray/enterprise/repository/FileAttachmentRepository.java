package com.uditray.enterprise.repository;

import com.uditray.enterprise.entity.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAttachmentRepository
        extends JpaRepository<FileAttachment, Long> {
}