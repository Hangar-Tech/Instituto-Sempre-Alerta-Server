package com.institutosemprealerta.semprealerta.infrastructure.entity.file;

import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@Getter
@Setter
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileDownloadUri;
    private String fileType;

    @CreationTimestamp
    private LocalDateTime uploadDate;

    public FileEntity(String fileName, String fileDownloadUri, String fileType) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
    }

    public FileEntity() {
    }

    public static FileEntity fromDomainToModel(File file) {
        return new FileEntity(
                file.getFileName(),
                file.getFileDownloadUri(),
                file.getFileType()
        );
    }

    public static FileResponse toResponse(FileEntity fileEntity) {
        return new FileResponse(
                fileEntity.getId(),
                fileEntity.getFileName(),
                fileEntity.getFileDownloadUri(),
                fileEntity.getFileType(),
                fileEntity.getUploadDate()
        );
    }
}
