package com.institutosemprealerta.semprealerta.infrastructure.config;

import com.institutosemprealerta.semprealerta.domain.ports.in.SlugGenerator;
import com.institutosemprealerta.semprealerta.domain.ports.in.impl.SlugifySlugGenerator;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    public SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes(
                        "Bearer Authentication",
                        createAPIKeyScheme()
                ))
                .info(new Info().title("Instituto Sempre Alerta API")
                        .description("API do Instituto Sempre Alerta.")
                        .version("1.0").contact(new Contact().name("Matheus Victor")
                                .email("matheusvictorhenrique@gmail.com")
                                .url("https://www.instituto-sempre-alerta.com.br/"))
                        .license(new License().name("License of API")
                                .url("https://opensource.org/license/mit/")));
    }
}
