package com.institutosemprealerta.semprealerta.infrastructure.repositories;

import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntity;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntityFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("JpaPostRepositoryTest")
class JpaPostRepositoryTest {

    @Autowired
    private JpaPostRepository jpaPostRepository;

    private final PostEntity postToCreate = PostEntityFactory.INSTANCE.createPostEntity(
            "title",
            "slug",
            "content",
            "banner"
    );

    @BeforeEach
    void setUp() {
        this.jpaPostRepository.save(postToCreate);
    }

    @AfterEach
    void tearDown() {
        this.jpaPostRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find post by slug")
    void should_find_post_by_slug() {
        PostEntity postFound = this.jpaPostRepository.findBySlug("slug").orElse(null);

        assertNotNull(postFound);
        assertEquals(postToCreate.getTitle(), postFound.getTitle());
        assertEquals(postToCreate.getSlug(), postFound.getSlug());
        assertEquals(postToCreate.getContent(), postFound.getContent());
        assertEquals(postToCreate.getBanner(), postFound.getBanner());
    }
}