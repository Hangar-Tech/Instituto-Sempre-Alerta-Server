package com.institutosemprealerta.semprealerta.infrastructure.repositories;

import com.institutosemprealerta.semprealerta.infrastructure.entity.file.FileEntity;
import com.institutosemprealerta.semprealerta.infrastructure.entity.file.mocks.FileMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("JpaFileRepositoryTest")
class JpaFileRepositoryTest {

    @Autowired
    private JpaFileRepository jpaFileRepository;


    @AfterEach
    void tearDown() {
        this.jpaFileRepository.deleteAll();
    }

    @Test
    @DisplayName("Should save file data to database")
    void should_save_file_data_to_database() {

        FileEntity fileToCreate = FileMocks.createFileEntity();
        FileEntity fileCreated = this.jpaFileRepository.save(fileToCreate);

        assertNotNull(fileCreated);
        assertEquals(fileToCreate.getFileName(), fileCreated.getFileName());
        assertEquals(fileToCreate.getFileDownloadUri(), fileCreated.getFileDownloadUri());
        assertEquals(fileToCreate.getFileType(), fileCreated.getFileType());
    }

    @Test
    @DisplayName("Should delete file by file id")
    void should_delete_file_by_file_id() {
        FileEntity fileToCreate = FileMocks.createFileEntity();
        FileEntity fileCreated = this.jpaFileRepository.save(fileToCreate);

       assertDoesNotThrow(() -> this.jpaFileRepository.deleteById(fileCreated.getId()));
    }
}