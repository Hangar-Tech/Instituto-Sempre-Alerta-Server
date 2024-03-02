package com.institutosemprealerta.semprealerta.domain.ports.out;

import com.institutosemprealerta.semprealerta.domain.model.Post;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepository {
    void save(Post post);

    void delete(Long id);

    void update(Long id, Post post);

    List<Post> listAll(Pageable pageable);

    Post findBySlug(String slug);
    Post findById(Long id);

}
