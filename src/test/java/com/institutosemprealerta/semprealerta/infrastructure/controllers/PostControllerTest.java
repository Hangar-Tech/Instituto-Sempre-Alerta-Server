package com.institutosemprealerta.semprealerta.infrastructure.controllers;

import com.institutosemprealerta.semprealerta.application.controllers.PostController;
import com.institutosemprealerta.semprealerta.domain.service.PostService;
import com.institutosemprealerta.semprealerta.domain.model.Post;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given a PostController")
class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    private final Post postMock = PostMocks.returnValidPostModel();

    @BeforeEach
    void setUp() {
        List<Post> posts = List.of(postMock);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> postPage = new PageImpl<>(posts, pageable, posts.size());

        when(postService.listAll(pageable)).thenReturn(postPage);
        when(postService.save(postMock)).thenReturn(postMock.getSlug());
        when(postService.findBySlug(postMock.getSlug())).thenReturn(postMock);
        doNothing().when(postService).update(postMock.getId(), postMock);
        doNothing().when(postService).delete(postMock.getId());
    }

    @AfterEach
    void tearDown() {
        reset(postService);
    }

    @Test
    @DisplayName("When getAllPosts, then return a pageable list of posts")
    void should_GetAllPosts_Successfully() {
        PageRequest pageable = PageRequest.of(0, 10);
        ResponseEntity<Page<Post>> postPage = postController.getAllPosts(pageable);

        assertNotNull(postPage);
        assertEquals(HttpStatus.OK, postPage.getStatusCode());
        assertEquals(1, Objects.requireNonNull(postPage.getBody()).getTotalElements());
        assertEquals(postMock, postPage.getBody().getContent().get(0));
    }

    @Test
    @DisplayName("When createPost, then return a URI")
    void should_CreatePost_Successfully() {
        ResponseEntity<?> response = postController.createPost(postMock);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/api/v1/posts/" + postMock.getSlug(), response.getHeaders().getLocation().getPath());
    }

    @Test
    @DisplayName("When getPostBySlug, then return a post")
    void should_GetPostBySlug_Successfully() {
        ResponseEntity<Post> response = postController.getPostBySlug(postMock.getSlug());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postMock, response.getBody());
    }

    @Test
    @DisplayName("When updatePost, then return no content")
    void should_UpdatePost_Successfully() {
        ResponseEntity<?> response = postController.updatePost(postMock.getId(), postMock);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(postService, times(1)).update(postMock.getId(), postMock);
    }

    @Test
    @DisplayName("When deletePost, then return no content")
    void should_DeletePost_Successfully() {
        ResponseEntity<?> response = postController.deletePost(postMock.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(postService, times(1)).delete(postMock.getId());
    }
}