package com.institutosemprealerta.semprealerta.application.service.impl;

import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.FileRepository;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import com.institutosemprealerta.semprealerta.domain.service.impl.StorageServiceImpl;
import com.institutosemprealerta.semprealerta.infrastructure.config.FileStorageProperties;
import com.institutosemprealerta.semprealerta.infrastructure.entity.file.mocks.FileMocks;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StorageServiceImplTest {

    private StorageServiceImpl storageService;

    @Mock
    private FileRepository fileRepository;

    @Mock
    private FileStorageProperties fileStorageProperties;

    @Mock
    private MultipartFile mockFile;

    @Mock
    private FileSystem mockFileSystem;

    @Mock
    private FileSystemProvider mockFileSystemProvider;

    private final FileResponse fileResponse = FileMocks.returnValidFileResponse();

    @BeforeEach
    void setUp() throws IOException {

        when(fileStorageProperties.getUploadDir()).thenReturn("pdf");
        storageService = new StorageServiceImpl(fileStorageProperties, fileRepository);

        when(mockFile.getOriginalFilename()).thenReturn("file.txt");
        when(mockFile.isEmpty()).thenReturn(false);
        when(mockFile.getSize()).thenReturn(100L);
        when(mockFile.getContentType()).thenReturn("text/plain");


        Path pathToDirectory = Paths.get("pdf");
        Path pathToFile = Paths.get("pdf/file.txt");
        if (!Files.exists(pathToDirectory)) {
            Files.createDirectories(pathToDirectory);
        }
        if (!Files.exists(pathToFile)) {
            Files.createFile(pathToFile);
        }

        when(mockFileSystem.provider()).thenReturn(mockFileSystemProvider);


        when(fileRepository.listAll()).thenReturn(List.of(fileResponse));
        doNothing().when(fileRepository).save(any(File.class));

    }

    @AfterEach
    void tearDown() {
        reset(
                fileRepository,
                fileStorageProperties,
                mockFile,
                mockFileSystem,
                mockFileSystemProvider
        );
    }

    @AfterAll
    static void afterAll() {
        Path pathToFile = Paths.get("pdf/file.txt");
        try {
            Files.deleteIfExists(pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void init() {
    }

    @Test
    @DisplayName("Should Store File With Valid Name")
    void should_Store_File_With_ValidName() {
        String fileName = storageService.store(mockFile, "text/plain");

        assertNotNull(fileName);
        assertEquals("file.txt", fileName);
        verify(fileRepository, times(1)).save(any(File.class));
    }

    @Test
    @DisplayName("should load file path")
    void should_Load_File_Path() {
        assertDoesNotThrow(() -> storageService.load("file.txt"));
    }

    @Test
    @DisplayName("Should Load All Files Successfully")
    void should_LoadAll_Files_Successfully() throws IOException {
        List<FileResponse> allFiles = storageService.loadAll();

        assertNotNull(allFiles);
        assertEquals(1, allFiles.size());
        assertEquals(fileResponse.fileName(), allFiles.get(0).fileName());
        verify(fileRepository, times(1)).listAll();
    }

    @Test
    @DisplayName("Should Load File Successfully")
    void should_loadAsResource_Successfully() {
        String fileName = "file.txt";

        Resource response = storageService.loadAsResource(fileName);

        assertNotNull(response);
    }

    @Test
    @DisplayName("Should Load File As Resource Successfully")
    void should_Delete_A_Resource() {
        String fileName = fileResponse.fileName();
        assertDoesNotThrow(() -> storageService.delete(fileName));

    }

    @Test
    @DisplayName("Should Delete File Successfully")
    void should_DeleteAll_Successfully() {
    }
}