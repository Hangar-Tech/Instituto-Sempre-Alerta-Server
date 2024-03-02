package com.institutosemprealerta.semprealerta.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

    private Long id;
    private String title;
    private String slug;

    private String content;
    private String banner;

    public Post(Long id, String title, String slug, String content, String banner) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.banner = banner;
    }

    public Post() {
    }
}
