package com.institutosemprealerta.semprealerta.infrastructure.entity.post.mocks;

import com.github.javafaker.Faker;
import com.institutosemprealerta.semprealerta.domain.model.Post;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntity;
import com.institutosemprealerta.semprealerta.infrastructure.entity.post.PostEntityFactory;

import java.time.LocalDateTime;

public class PostMocks {
    private static final Faker faker = new Faker();

    private static final Long id = faker.number().randomNumber();
    private static final String title = faker.lorem().sentence();
    private static final String slug = faker.internet().slug();
    private static final String content = faker.lorem().paragraph();
    private static final String banner = faker.internet().image();
    private static final LocalDateTime createdAt = LocalDateTime.now();

    public static PostEntity returnValidPostEntity() {
        return PostEntityFactory.INSTANCE.createPostEntity(
                id,
                title,
                slug,
                content,
                banner,
                createdAt
        );
    }

    public static PostEntity returnValidPostToBeCreated() {
        return PostEntityFactory.INSTANCE.createPostEntity(
                title,
                slug,
                content,
                banner
        );
    }

    public static PostEntity returnValidPostToBeUpdated() {
        return PostEntityFactory.INSTANCE.createPostEntity(
                id,
                title,
                slug,
                faker.dune().saying(),
                banner,
                createdAt
        );
    }

    public static Post returnValidPostModel() {
        return new Post(
                id,
                title,
                slug,
                content,
                banner,
                createdAt
        );
    }

    public static Post returnValidPostModelToBeCreated() {
        return new Post(
                null,
                title,
                slug,
                content,
                banner,
                null
        );
    }

    public static Post returnValidPostModelToBeUpdated() {
        return new Post(
                id,
                title,
                slug,
                faker.dune().saying(),
                banner,
                createdAt
        );
    }
}
