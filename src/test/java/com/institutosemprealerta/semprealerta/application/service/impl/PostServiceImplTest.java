package com.institutosemprealerta.semprealerta.application.service.impl;

import com.institutosemprealerta.semprealerta.domain.model.Post;
import com.institutosemprealerta.semprealerta.domain.ports.in.SlugGenerator;
import com.institutosemprealerta.semprealerta.domain.ports.out.PostRepository;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntity;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.mocks.PostMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Post Service Test")
class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private SlugGenerator slugGenerator;

    private final Post postMock = PostMocks.returnValidPostModel();

    @BeforeEach
    void setUp() {
        List<Post> posts = List.of(postMock);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> postPage = new PageImpl<>(posts, pageable, posts.size());

        when(postService.listAll(any(Pageable.class))).thenReturn(postPage);
        when(postRepository.save(any(Post.class))).thenReturn(postMock.getSlug());
        when(postRepository.findBySlug(anyString())).thenReturn(postMock);
        when(postRepository.findById(anyLong())).thenReturn(postMock);
        doNothing().when(postRepository).delete(anyLong());
        doNothing().when(postRepository).update(anyLong(), any(Post.class));

        when(slugGenerator.generate(anyString())).thenReturn(postMock.getSlug());
    }

    @AfterEach
    void tearDown() {
        reset(postRepository, slugGenerator);
    }

    @Test
    @DisplayName("Should save a post successfully")
    void should_SaveAPost_Successfully() {
        Post postToCreate = PostMocks.returnValidPostModelToBeCreated();
        String slug = postService.save(postToCreate);

        assertNotNull(slug);
        assertEquals(postToCreate.getSlug(), slug);
        verify(slugGenerator, times(1)).generate(postToCreate.getTitle());
    }

    @Test
    @DisplayName("Should delete a post successfully")
    void should_DeleteAPost_Successfully() {
        assertDoesNotThrow(() -> postService.delete(postMock.getId()));
        verify(postRepository, times(1)).delete(postMock.getId());
    }

    @Test
    @DisplayName("Should update a post successfully")
    void should_UpdateAPost_Successfully() {
        Post postToUpdate = PostMocks.returnValidPostModelToBeUpdated();

        assertDoesNotThrow(() -> postService.update(postMock.getId(), postToUpdate));
        verify(postRepository, times(1)).update(postMock.getId(), postToUpdate);
        verify(slugGenerator, times(1)).generate(postToUpdate.getTitle());
    }

    @Test
    @DisplayName("Should list all pageable successfully")
    void should_ListAllPageable_Successfully() {
        PageRequest pageable = PageRequest.of(0, 10);

        Page<Post> posts = postService.listAll(pageable);

        assertNotNull(posts);
        assertEquals(1, posts.getTotalElements());
        assertEquals(postMock.getId(), posts.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should find by slug successfully")
    void should_FindBySlug_Successfully() {
        Post post = postService.findBySlug(postMock.getSlug());

        assertNotNull(post);
        assertEquals(postMock.getId(), post.getId());
        assertEquals(postMock.getTitle(), post.getTitle());
        assertEquals(postMock.getSlug(), post.getSlug());
        assertEquals(postMock.getContent(), post.getContent());
        assertEquals(postMock.getBanner(), post.getBanner());
    }

    @Test
    @DisplayName("Should find by id successfully")
    void should_FindById_Successfully() {
        Post post = postService.findById(postMock.getId());

        assertNotNull(post);
        assertEquals(postMock.getId(), post.getId());
        assertEquals(postMock.getTitle(), post.getTitle());
        assertEquals(postMock.getSlug(), post.getSlug());
        assertEquals(postMock.getContent(), post.getContent());
        assertEquals(postMock.getBanner(), post.getBanner());
    }
}