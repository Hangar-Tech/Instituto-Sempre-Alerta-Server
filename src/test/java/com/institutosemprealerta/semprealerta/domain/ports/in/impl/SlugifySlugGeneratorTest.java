package com.institutosemprealerta.semprealerta.domain.ports.in.impl;

import com.github.slugify.Slugify;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Slugify slug generator test")
class SlugifySlugGeneratorTest {

    @InjectMocks
    private SlugifySlugGenerator slugifySlugGenerator;

    @Mock
    Slugify slugify;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void turnDown() {
        reset(slugify);
    }

    @Test
    @DisplayName("Should generate slug successfully")
    void should_GenerateSlug_Successfully() {
        String input = "Test Input";
        String expectedSlug = "test-input";
        when(slugify.slugify(input)).thenReturn(expectedSlug);

        String result = slugifySlugGenerator.generate(input);
        assertEquals(expectedSlug, result);
    }
}