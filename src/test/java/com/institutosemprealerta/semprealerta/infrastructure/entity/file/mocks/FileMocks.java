package com.institutosemprealerta.semprealerta.infrastructure.entity.file.mocks;

import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import com.institutosemprealerta.semprealerta.infrastructure.entity.file.FileEntity;

import java.time.LocalDateTime;

public class FileMocks {
    public static FileEntity createFileEntity() {
        return new FileEntity("fileName", "fileDownloadUri", "fileType");
    }

    public static File createFile() {
        return File.builder()
                .fileName("fileName")
                .fileDownloadUri("fileDownloadUri")
                .fileType("fileType")
                .build();
    }

    public static FileResponse returnValidFileResponse() {
        return new FileResponse(1L, "file.txt", "/api/v1/download/1", "fileType", LocalDateTime.now());
    }
}
