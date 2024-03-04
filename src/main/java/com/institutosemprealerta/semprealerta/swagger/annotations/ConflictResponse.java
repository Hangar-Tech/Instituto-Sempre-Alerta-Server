package com.institutosemprealerta.semprealerta.swagger.annotations;

import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.ExceptionPattern;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "409", description = "There are some conflicts",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionPattern.class)))
public @interface ConflictResponse {
}
