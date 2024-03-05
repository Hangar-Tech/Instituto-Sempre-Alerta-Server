package com.institutosemprealerta.semprealerta.domain.ports.out.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "email is mandatory")
        @Schema(description = "email do usuário", example = "muryllo@email.com")
        String email,
        @NotBlank(message = "password is mandatory")
        @Schema(description = "Senha do usuário", example = "12345")
        String password
) {
}
