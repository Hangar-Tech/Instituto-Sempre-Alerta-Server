package com.institutosemprealerta.semprealerta.domain.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {

    @Hidden
    private Long id;

    @NotBlank(message = "Title é obrigatorio")
    @Schema(description = "Title do post", example = "Titulo do post")
    private String title;

    private String slug;

    @NotBlank(message = "Content é obrigatorio")
    @Schema(description = "Contenteudo do post", example = "Conteudo do post")
    private String content;

    @NotBlank(message = "Banner é obrigatorio")
    @Schema(description = "Banner do post", example = "https://www.https://github.com/MatheusVict.png")
    private String banner;

    @Hidden
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
