package com.institutosemprealerta.semprealerta.infrastructure.entity.post;

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


}
