package com.institutosemprealerta.semprealerta.application.controllers;

import com.institutosemprealerta.semprealerta.domain.service.UserService;
import com.institutosemprealerta.semprealerta.domain.model.UserDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.UserResponse;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
        userService.save(user.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable int id) {
        User userFound = userService.findById(id);
        return ResponseEntity.ok().body(UserResponse.toResponse(userFound));
    }

    @GetMapping("/registration/{reg}")
    public ResponseEntity<UserResponse> findByRegistration(@PathVariable String reg) {
        User userFound = userService.findByRegistration(reg);
        return ResponseEntity.ok().body(UserResponse.toResponse(userFound));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        userService.update(id, user.toDomain());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}