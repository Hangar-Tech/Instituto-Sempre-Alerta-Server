package com.institutosemprealerta.semprealerta.infrastructure.adpters;

import com.institutosemprealerta.semprealerta.domain.model.Post;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntity;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.mocks.PostMocks;
import com.institutosemprealerta.semprealerta.infrastructure.repositories.JpaPostRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("JpaPostRepositoryAdapterTest")
class JpaPostRepositoryAdapterTest {

    @InjectMocks
    private JpaPostRepositoryAdapter jpaPostRepositoryAdapter;

    @Mock
    private JpaPostRepository jpaPostRepository;

    private final PostEntity postMocks = PostMocks.returnValidPostEntity();

    @BeforeEach
    void setUp() {
        List<PostEntity> postEntityList = List.of(postMocks);

        Pageable pageable = PageRequest.of(0, 10);
        Page<PostEntity> postEntityPage = new PageImpl<>(postEntityList, pageable, postEntityList.size());

        when(jpaPostRepository.findAll(any(Pageable.class))).thenReturn(postEntityPage);
        when(jpaPostRepository.save(any(PostEntity.class))).thenReturn(postMocks);
        when(jpaPostRepository.findBySlug(any(String.class))).thenReturn(java.util.Optional.of(postMocks));
        when(jpaPostRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(postMocks));
        doNothing().when(jpaPostRepository).deleteById(any(Long.class));
    }

    @AfterEach
    void tearDown() {
        reset(jpaPostRepository);
    }

    @Test
    @DisplayName("Should Save A Post Successfully")
    void should_Save_A_Post_Successfully() {
        PostEntity postEntity = PostMocks.returnValidPostToBeCreated();

        String slug = jpaPostRepositoryAdapter.save(PostEntity.toModel(postEntity));

        assertNotNull(slug);
        assertEquals(postEntity.getSlug(), slug);
    }

    @Test
    @DisplayName("Should Delete A Post Successfully")
    void should_Delete_A_Post_Successfully() {
        assertDoesNotThrow(() -> jpaPostRepositoryAdapter.delete(postMocks.getId()));
    }

    @Test
    @DisplayName("Should Update A Post Successfully")
    void should_Update_A_Post_Successfully() {
        PostEntity postEntity = PostMocks.returnValidPostToBeUpdated();

        assertDoesNotThrow(() -> jpaPostRepositoryAdapter.update(postMocks.getId(), PostEntity.toModel(postEntity)));
    }

    @Test
    @DisplayName("Should ListAll PageablePost Successfully")
    void should_ListAll_PageablePost_Successfully() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Post> postPage = jpaPostRepositoryAdapter.listAll(pageable);

        assertNotNull(postPage);
        assertEquals(1, postPage.getTotalElements());
        assertEquals(postMocks.getId(), postPage.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should Find A PostBySlug Successfully")
    void should_Find_A_PostBySlug_Successfully() {
        Post post = jpaPostRepositoryAdapter.findBySlug(postMocks.getSlug());

        assertNotNull(post);
        assertEquals(postMocks.getId(), post.getId());
        assertEquals(postMocks.getTitle(), post.getTitle());
        assertEquals(postMocks.getSlug(), post.getSlug());
        assertEquals(postMocks.getContent(), post.getContent());
        assertEquals(postMocks.getBanner(), post.getBanner());
    }

    @Test
    @DisplayName("Should Find A Post By Id Successfully")
    void should_Find_A_Post_ById_Successfully() {
        Post post = jpaPostRepositoryAdapter.findById(postMocks.getId());

        assertNotNull(post);
        assertEquals(postMocks.getId(), post.getId());
        assertEquals(postMocks.getTitle(), post.getTitle());
        assertEquals(postMocks.getSlug(), post.getSlug());
        assertEquals(postMocks.getContent(), post.getContent());
        assertEquals(postMocks.getBanner(), post.getBanner());
    }
}