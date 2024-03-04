package com.institutosemprealerta.semprealerta.infrastructure.adpters;

import com.institutosemprealerta.semprealerta.domain.model.Post;
import com.institutosemprealerta.semprealerta.domain.ports.out.PostRepository;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.post.PostNotFoundException;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntity;
import com.institutosemprealerta.semprealerta.infrastructure.repositories.JpaPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaPostRepositoryAdapter implements PostRepository {
    private final JpaPostRepository jpaPostRepository;

    public JpaPostRepositoryAdapter(JpaPostRepository jpaPostRepository) {
        this.jpaPostRepository = jpaPostRepository;
    }

    @Override
    public String save(Post post) {
        PostEntity postToSave = PostEntity.fromModel(post);
        PostEntity postSaved = jpaPostRepository.save(postToSave);
        return postSaved.getSlug();
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        jpaPostRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Post post) {
        this.findById(id);
        PostEntity postToUpdate = PostEntity.fromModel(post);
        postToUpdate.setId(id);
        jpaPostRepository.save(postToUpdate);
    }

    @Override
    public Page<Post> listAll(Pageable pageable) {
        return jpaPostRepository.findAll(pageable)
                .map(postEntity -> PostEntity.toModel(postEntity));
    }

    @Override
    public Post findBySlug(String slug) {
        return jpaPostRepository.findBySlug(slug)
                .map(postEntity -> PostEntity.toModel(postEntity))
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    @Override
    public Post findById(Long id) {
        return jpaPostRepository.findById(id)
                .map(postEntity -> PostEntity.toModel(postEntity))
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
    }
}
