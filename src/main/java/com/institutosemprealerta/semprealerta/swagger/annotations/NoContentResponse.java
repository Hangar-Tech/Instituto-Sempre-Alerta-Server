package com.institutosemprealerta.semprealerta.swagger.annotations;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "204", description = "Requisição bem sucedida e sem retorno", useReturnTypeSchema = true)
public @interface NoContentResponse {
}
