package com.institutosemprealerta.semprealerta.infrastructure.entity.file;

import com.institutosemprealerta.semprealerta.domain.model.File;

public class FileEntityFactory {
    public static final FileEntityFactory INSTANCE = new FileEntityFactory();

    private FileEntityFactory() {
    }

    public FileEntity create(File file) {
        return new FileEntity(file.getFileName(), file.getFileDownloadUri(), file.getFileType());
    }

    public FileEntity create(
            String fileName,
            String fileDownloadUri,
            String fileType
    ) {
        return new FileEntity(fileName, fileDownloadUri, fileType);
    }

    public FileEntity create() {
        return new FileEntity();
    }
}
