package com.institutosemprealerta.semprealerta.infrastructure.controllers;

import com.institutosemprealerta.semprealerta.application.controllers.FilesStorageController;
import com.institutosemprealerta.semprealerta.domain.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilesStorageControllerTest {

    @InjectMocks
    FilesStorageController filesStorageController;

    @Mock
    StorageService storageService;

    @Mock
    Resource mockResource;

    @Mock
    HttpServletRequest mockRequest;


    private final MultipartFile mockFile = new MockMultipartFile("file.txt", "content".getBytes());

    @BeforeEach
    void setUp() throws IOException {
        String contentFile = "Hello, World!";
        InputStream is = new ByteArrayInputStream(contentFile.getBytes(StandardCharsets.UTF_8));
        when(mockResource.getInputStream()).thenReturn(is);

        when(mockResource.getFilename()).thenReturn("file.txt");
        when(storageService.store(any(), any())).thenReturn("file.txt");

        Path tempFile = Files.createTempFile("temp", ".txt");
        File file = tempFile.toFile();
        when(mockResource.getFile()).thenReturn(file);

        when(storageService.loadAsResource(any())).thenReturn(mockResource);
    }

    @AfterEach
    void tearDown() {
        reset(storageService, mockResource);
    }

    @Test
    @DisplayName("Should upload file successfully")
    void should_UploadFile_Successfully() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        ServletRequestAttributes sra = new ServletRequestAttributes(request, response);
        RequestContextHolder.setRequestAttributes(sra);

        String fileType = "pdf";
        String expected = "File uploaded successfully, file name: file.txt on path: http://localhost/api/v1/files/download/file.txt";
        ResponseEntity<String> responseEntity = filesStorageController.uploadFile(mockFile, fileType);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    @DisplayName("Should download file successfully")
    void should_DownloadFile_Successfully() {
        MockHttpServletRequest request = new MockHttpServletRequest();

        ResponseEntity<Resource> response = filesStorageController.downloadFile("file.txt", request);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("attachment; filename=\"file.txt\"", response.getHeaders().get(HttpHeaders.CONTENT_DISPOSITION).get(0));
    }

    @Test
    @DisplayName("Should list files successfully")
    void should_ListFiles_Successfully() {
    }
}