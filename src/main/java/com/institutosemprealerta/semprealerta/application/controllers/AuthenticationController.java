package com.institutosemprealerta.semprealerta.application.controllers;

import com.institutosemprealerta.semprealerta.domain.ports.out.request.LoginDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.LoginResponse;
import com.institutosemprealerta.semprealerta.domain.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "You can login with your email and password")

    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginRequestBody) {
        LoginResponse token = authenticationService.login(loginRequestBody);
        return ResponseEntity.ok(token);
    }
}
