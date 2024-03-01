package com.institutosemprealerta.semprealerta.infrastructure.config;

import com.institutosemprealerta.semprealerta.domain.ports.in.SlugGenerator;
import com.institutosemprealerta.semprealerta.domain.ports.in.impl.SlugifySlugGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public SlugGenerator slugGenerator() {
        return new SlugifySlugGenerator();
    }
}
