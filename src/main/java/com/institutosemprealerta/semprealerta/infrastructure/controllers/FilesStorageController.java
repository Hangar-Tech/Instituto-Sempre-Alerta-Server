package com.institutosemprealerta.semprealerta.infrastructure.controllers;

import com.institutosemprealerta.semprealerta.application.service.StorageService;
import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/v1/files")
public class FilesStorageController {
    private StorageService storageService;

    public FilesStorageController(StorageService storageService) {

        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("file_type") String fileType) {

        String fileName = storageService.store(file, fileType);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/files/download/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok("File uploaded successfully, file name: " + fileName + " on path: " + fileDownloadUri);
    }

    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName,
            HttpServletRequest request
    ) {

        try {
            Resource resource = storageService.loadAsResource(fileName);
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            if (contentType == null) {
                contentType = MediaType.APPLICATION_OCTET_STREAM.getType();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileResponse>> listFiles() throws IOException {
        List<FileResponse> fileNames = storageService.loadAll();

        return ResponseEntity.ok(fileNames);
    }
}
