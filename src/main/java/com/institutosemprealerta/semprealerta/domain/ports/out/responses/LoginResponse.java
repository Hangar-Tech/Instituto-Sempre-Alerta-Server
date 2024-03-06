package com.institutosemprealerta.semprealerta.domain.ports.out.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(description = "token de autorização para acessar os recursos", example = "eyn32nklafjçj354335g35")
        @JsonProperty("access_token")
        String accessToken
) {
}
