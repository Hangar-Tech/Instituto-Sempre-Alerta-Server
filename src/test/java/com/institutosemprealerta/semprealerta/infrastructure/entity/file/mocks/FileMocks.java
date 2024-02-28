package com.institutosemprealerta.semprealerta.infrastructure.entity.file.mocks;

import com.institutosemprealerta.semprealerta.infrastructure.entity.file.FileEntity;

public class FileMocks {
    public static FileEntity createFileEntity() {
        return new FileEntity("fileName", "fileDownloadUri", "fileType");
    }
}
