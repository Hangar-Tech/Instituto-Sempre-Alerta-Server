package com.institutosemprealerta.semprealerta.infrastructure.adpters;

import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.FileRepository;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;
import com.institutosemprealerta.semprealerta.infrastructure.entity.file.FileEntity;
import com.institutosemprealerta.semprealerta.infrastructure.repositories.JpaFileRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaFileRepositoryAdapter implements FileRepository {

    private final JpaFileRepository fileRepository;

    public JpaFileRepositoryAdapter(JpaFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void save(File file) {
        FileEntity fileEntity = FileEntity.fromDomainToModel(file);
        fileRepository.save(fileEntity);
    }

    @Override
    public void delete(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public List<FileResponse> listAll() {
        return fileRepository.findAll().stream()
                .map(FileEntity::toResponse)
                .toList();
    }
}
