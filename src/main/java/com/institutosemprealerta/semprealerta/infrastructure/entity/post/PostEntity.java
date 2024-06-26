package com.institutosemprealerta.semprealerta.infrastructure.entity.post;

import com.institutosemprealerta.semprealerta.domain.model.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String slug;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String banner;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public PostEntity() {
    }

    public PostEntity(Long id, String title, String slug, String content, String banner, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.banner = banner;
        this.createdAt = createdAt;
    }

    public PostEntity(String title, String slug, String content, String banner) {
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.banner = banner;
    }

    public static PostEntity fromModel(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(post.getId());
        postEntity.setTitle(post.getTitle());
        postEntity.setSlug(post.getSlug());
        postEntity.setContent(post.getContent());
        postEntity.setBanner(post.getBanner());
        return postEntity;
    }

    public static Post toModel(PostEntity postEntity) {
        return new Post(postEntity.getId(), postEntity.getTitle(), postEntity.getSlug(), postEntity.getContent(), postEntity.getBanner(), postEntity.getCreatedAt());
    }

    public Post toModel() {
        return new Post(this.getId(), this.getTitle(), this.getSlug(), this.getContent(), this.getBanner(), this.getCreatedAt());
    }

}
