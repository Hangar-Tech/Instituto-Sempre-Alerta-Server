package com.institutosemprealerta.semprealerta.infrastructure.adpters;

import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import com.institutosemprealerta.semprealerta.infrastructure.entity.file.FileEntity;
import com.institutosemprealerta.semprealerta.infrastructure.entity.file.FileEntityFactory;
import com.institutosemprealerta.semprealerta.infrastructure.entity.file.mocks.FileMocks;
import com.institutosemprealerta.semprealerta.infrastructure.repositories.JpaFileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaFileRepositoryAdapterTest {

    @InjectMocks
    private JpaFileRepositoryAdapter jpaFileRepositoryAdapter;

    @Mock
    private JpaFileRepository jpaFileRepository;

    private final FileEntity validFile = FileEntityFactory.INSTANCE.create(
            "file.txt",
            "http://localhost:8080/api/v1/files/download/1",
            "text/plain"
    );

    @BeforeEach
    void setUp() {

        when(jpaFileRepository.save(ArgumentMatchers.any(FileEntity.class))).thenReturn(validFile);
        when(jpaFileRepository.findAll()).thenReturn(List.of(validFile));
        doNothing().when(jpaFileRepository).deleteById(ArgumentMatchers.anyLong());
    }

    @AfterEach
    void tearDown() {
        reset(jpaFileRepository);
    }

    @Test
    void save() {
        File fileToCreate = FileMocks.createFile();
        assertDoesNotThrow(() -> jpaFileRepositoryAdapter.save(fileToCreate));
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> jpaFileRepositoryAdapter.delete(1L));
    }

    @Test
    void listAll() {
        validFile.setId(1L);
        List<FileResponse> fileResponses = jpaFileRepositoryAdapter.listAll();

        assertNotNull(fileResponses);
        assertFalse(fileResponses.isEmpty());
        assertEquals(validFile.getId(), fileResponses.get(0).id());
    }
}