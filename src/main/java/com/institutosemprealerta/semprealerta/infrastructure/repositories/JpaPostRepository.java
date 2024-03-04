package com.institutosemprealerta.semprealerta.infrastructure.repositories;

import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPostRepository extends JpaRepository<PostEntity, Long>{
    Optional<PostEntity> findBySlug(String slug);
}
