package com.institutosemprealerta.semprealerta.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {

    private Long id;
    @NotBlank
    private String title;
    private String slug;

    @NotBlank
    private String content;
    @NotBlank
    private String banner;
    private LocalDateTime createdAt;

    public Post(Long id, String title, String slug, String content, String banner, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.banner = banner;
        this.createdAt = createdAt;
    }

    public Post() {
    }
}
