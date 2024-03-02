package com.institutosemprealerta.semprealerta.infrastructure.config;

import com.institutosemprealerta.semprealerta.domain.ports.in.SlugGenerator;
import com.institutosemprealerta.semprealerta.domain.ports.in.impl.SlugifySlugGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Configuration
public class ApplicationConfig {

    @Bean
    public SlugGenerator slugGenerator() {
        return new SlugifySlugGenerator();
    }

    @Bean
    public Pageable defaultPageable() {
        return PageRequest.of(0, 10, Sort.by("createdAt").descending());
    }
}
