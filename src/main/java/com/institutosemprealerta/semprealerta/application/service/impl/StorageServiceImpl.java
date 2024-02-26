package com.institutosemprealerta.semprealerta.application.service.impl;

import com.institutosemprealerta.semprealerta.application.service.StorageService;
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
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {
    private final Path fileStorageLocation;

    public StorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
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
    public String store(MultipartFile file) {
        if (file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
            throw new RuntimeException("Failed to store empty file " + file.getOriginalFilename());
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Path targetLocation = fileStorageLocation.resolve(fileName);
            init();
            file.transferTo(targetLocation.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
        return fileName;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.fileStorageLocation, 1)
                    .filter(path -> !path.equals(this.fileStorageLocation))
                    .map(this.fileStorageLocation::relativize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path load(String filename) {
        Path file = fileStorageLocation.resolve(filename).normalize();
        if (!Files.exists(file)) {
            throw new RuntimeException("File not found " + filename);
        }
        return file;
    }

    @Override
    public Resource loadAsResource(String filename) {
        URI fileUri = load(filename).toUri();
        try {
            return new UrlResource(fileUri);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String filename) {
        Path file = load(filename);

        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(fileStorageLocation.toFile());
    }
}
