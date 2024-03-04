package com.institutosemprealerta.semprealerta.application.controllers;

import com.institutosemprealerta.semprealerta.domain.service.StorageService;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import com.institutosemprealerta.semprealerta.swagger.annotations.BadRequestResponse;
import com.institutosemprealerta.semprealerta.swagger.annotations.CreatedResponse;
import com.institutosemprealerta.semprealerta.swagger.annotations.NotFoundResponse;
import com.institutosemprealerta.semprealerta.swagger.annotations.OkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/files")
@Tag(name = "Files", description = "Files management")
public class FilesStorageController {
    private StorageService storageService;

    public FilesStorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Operation(summary = "Upload a file", description = "Upload a file to the server")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CreatedResponse
    @BadRequestResponse
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("file_type") String fileType) {

        String fileName = storageService.store(file, fileType);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/files/download/")
                .path(fileName)
                .toUriString();

        URI uri = URI.create(fileDownloadUri);
        return ResponseEntity.created(uri).body("File uploaded successfully, file name: " + fileName + " on path: " + fileDownloadUri);
    }

    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    @Operation(summary = "Download a file", description = "Download a file from the server")
    @OkResponse
    @NotFoundResponse
    @BadRequestResponse
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
    @Operation(summary = "List all files", description = "List all files from the server")
    @OkResponse
    @BadRequestResponse
    public ResponseEntity<List<FileResponse>> listFiles() throws IOException {
        List<FileResponse> fileNames = storageService.loadAll();

        return ResponseEntity.ok(fileNames);
    }
}
