package com.institutosemprealerta.semprealerta.domain.ports.in.impl;

import com.github.slugify.Slugify;
import com.institutosemprealerta.semprealerta.domain.ports.in.SlugGenerator;
import org.springframework.stereotype.Component;

@Component
public class SlugifySlugGenerator implements SlugGenerator {
    private final Slugify slug;

    public SlugifySlugGenerator() {
        this.slug = Slugify.builder().build();
    }

    @Override
    public String generate(String input) {
        return slug.slugify(input);
    }
}
