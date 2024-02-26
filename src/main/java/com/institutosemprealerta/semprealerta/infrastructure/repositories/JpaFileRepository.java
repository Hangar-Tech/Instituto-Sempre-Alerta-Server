package com.institutosemprealerta.semprealerta.infrastructure.repositories;

import com.institutosemprealerta.semprealerta.infrastructure.entity.file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFileRepository extends JpaRepository<FileEntity, Long> {
}
