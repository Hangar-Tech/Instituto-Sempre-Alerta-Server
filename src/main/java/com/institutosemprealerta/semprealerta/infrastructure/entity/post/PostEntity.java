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
@Table(name = "posts")
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
    private LocalDateTime createdAt;

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
        return new Post(postEntity.getId(), postEntity.getTitle(), postEntity.getSlug(), postEntity.getContent(), postEntity.getBanner());
    }

}
