package com.institutosemprealerta.semprealerta.application.controllers;

import com.institutosemprealerta.semprealerta.domain.service.PostService;
import com.institutosemprealerta.semprealerta.domain.model.Post;
import com.institutosemprealerta.semprealerta.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/posts")
@Tag(name = "Post", description = "Post management")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Lista de todos os posts", description = "Lista de todos os posts com paginação")
    @OkResponse
    public ResponseEntity<Page<Post>> getAllPosts(Pageable pageable) {
        return ResponseEntity.ok(postService.listAll(pageable));
    }

    @PostMapping
    @Operation(summary = "Criar postagem", description = "Crie uma nova postagem")
    @CreatedResponse
    @BadRequestResponse
    public ResponseEntity<?> createPost(@Valid @RequestBody Post post) {
        String slug = postService.save(post);
        return ResponseEntity.created(URI.create("/api/v1/posts/" + slug)).build();
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Pegar post pelo slug", description = "Procura um post pelo seu slug")
    @OkResponse
    @NotFoundResponse
    public ResponseEntity<Post> getPostBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(postService.findBySlug(slug));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar post", description = "Atualize um post existente pelo seu id")
    @NoContentResponse
    @NotFoundResponse
    @BadRequestResponse
    public ResponseEntity<?> updatePost(@PathVariable Long id, @Valid @RequestBody Post post) {
        postService.update(id, post);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar post", description = "Deleta um post existente pelo seu id")
    @NoContentResponse
    @NotFoundResponse
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
