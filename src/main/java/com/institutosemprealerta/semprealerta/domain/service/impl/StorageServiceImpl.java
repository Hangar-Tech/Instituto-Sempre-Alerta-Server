package com.institutosemprealerta.semprealerta.domain.service.impl;

import com.institutosemprealerta.semprealerta.domain.service.StorageService;
import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.FileRepository;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.file.FileNotFoundException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.file.InvalidFileException;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import com.institutosemprealerta.semprealerta.infrastructure.config.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    private final Path fileStorageLocation;
    private final FileRepository fileRepository;

    public StorageServiceImpl(FileStorageProperties fileStorageProperties, FileRepository fileRepository) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.fileRepository = fileRepository;
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Override
    public String store(MultipartFile file, String fileType) {
        if (file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
            throw new InvalidFileException("File name is empty");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Path targetLocation = fileStorageLocation.resolve(fileName);
            init();

            file.transferTo(targetLocation.toFile());

            String fileDownloadUri = "/api/v1/files/download/" + fileName;

            File fileData = File.builder()
                    .fileName(fileName)
                    .fileType(fileType)
                    .fileDownloadUri(fileDownloadUri)
                    .build();

            this.fileRepository.save(fileData);
        } catch (IOException e) {
            throw new InvalidFileException("Could not store file " + fileName + ". Please try again!");
        }
        return fileName;
    }

    @Override
    public List<FileResponse> loadAll() {

        return this.fileRepository.listAll();
    }

    @Override
    public Path load(String filename) {
        Path file = fileStorageLocation.resolve(filename).normalize();
        if (!Files.exists(file)) {
            throw new FileNotFoundException("File not found " + filename);
        }
        return file;
    }

    @Override
    public Resource loadAsResource(String filename) {
        URI fileUri = load(filename).toUri();
        try {
            return new UrlResource(fileUri);
        } catch (MalformedURLException e) {
            throw new InvalidFileException("Throwing exception when trying to read file " + filename + e.getMessage());
        }
    }

    @Override
    public void delete(String filename) {
        Path file = load(filename);
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new FileNotFoundException("File not found " + filename);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(fileStorageLocation.toFile());
    }
}
