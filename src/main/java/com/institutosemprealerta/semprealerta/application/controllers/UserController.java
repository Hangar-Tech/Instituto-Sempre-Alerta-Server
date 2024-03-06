package com.institutosemprealerta.semprealerta.application.controllers;

import com.institutosemprealerta.semprealerta.domain.service.UserService;
import com.institutosemprealerta.semprealerta.domain.model.UserDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.UserResponse;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "User", description = "Administração de usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Criação de um usuário", description = "Criação de um usuário no sistema")
    @CreatedResponse
    @ConflictResponse
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
        userService.save(user.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca de um usuário", description = "Busca de um usuário pelo id")
    @OkResponse
    @NotFoundResponse
    public ResponseEntity<UserResponse> findById(@PathVariable int id) {
        User userFound = userService.findById(id);
        return ResponseEntity.ok().body(UserResponse.toResponse(userFound));
    }

    @GetMapping("/registration/{reg}")
    @Operation(summary = "Busca de um usuário", description = "Busca de um usuário pela matrícula")
    @OkResponse
    @NotFoundResponse
    public ResponseEntity<UserResponse> findByRegistration(@PathVariable String reg) {
        User userFound = userService.findByRegistration(reg);
        return ResponseEntity.ok().body(UserResponse.toResponse(userFound));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de um usuário", description = "Atualização de um usuário pelo id")
    @NoContentResponse
    @NotFoundResponse
    @ConflictResponse
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        userService.update(id, user.toDomain());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleção de um usuário", description = "Deleção de um usuário pelo id")
    @NoContentResponse
    @NotFoundResponse
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
