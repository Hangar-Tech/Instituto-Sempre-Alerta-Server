package com.institutosemprealerta.semprealerta.domain.service.impl;

import com.institutosemprealerta.semprealerta.domain.service.PostService;
import com.institutosemprealerta.semprealerta.domain.model.Post;
import com.institutosemprealerta.semprealerta.domain.ports.in.SlugGenerator;
import com.institutosemprealerta.semprealerta.domain.ports.out.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final SlugGenerator slugGenerator;

    public PostServiceImpl(PostRepository postRepository, SlugGenerator slugGenerator) {
        this.postRepository = postRepository;
        this.slugGenerator = slugGenerator;
    }

    @Override
    public String save(Post post) {
        String slug = this.generateSlug(post.getTitle());
        post.setSlug(slug);

        return postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(id);
    }

    @Override
    public void update(Long id, Post post) {
        String slug = this.generateSlug(post.getTitle());
        post.setSlug(slug);
        postRepository.update(id, post);
    }

    @Override
    public Page<Post> listAll(Pageable pageable) {
        return postRepository.listAll(pageable);
    }


    @Override
    public Post findBySlug(String slug) {
        return postRepository.findBySlug(slug);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    private String generateSlug(String title) {
        return  slugGenerator.generate(title);
    }
}
