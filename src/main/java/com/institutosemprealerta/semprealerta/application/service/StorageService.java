package com.institutosemprealerta.semprealerta.application.service;

import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {
    void init();
    String store(MultipartFile file, String fileType);
    List<FileResponse> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void delete(String filename);
    void deleteAll();
}
