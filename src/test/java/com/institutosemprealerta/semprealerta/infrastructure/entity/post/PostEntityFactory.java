package com.institutosemprealerta.semprealerta.infrastructure.entity.post;

import java.time.LocalDateTime;

public class PostEntityFactory {
    public static PostEntityFactory INSTANCE = new PostEntityFactory();

    private PostEntityFactory() {
    }

    public PostEntity createPostEntity() {
        return new PostEntity();
    }

    public PostEntity createPostEntity(
            String title,
            String slug,
            String content,
            String banner
    ) {
        return new PostEntity(
                title,
                slug,
                content,
                banner
        );
    }

    public PostEntity createPostEntity(
            Long id,
            String title,
            String slug,
            String content,
            String banner,
            LocalDateTime createdAt
    ) {
        return new PostEntity(
                id,
                title,
                slug,
                content,
                banner,
                createdAt
        );
    }


}
