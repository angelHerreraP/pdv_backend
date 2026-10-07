package com.pdv.pdv_backend.categoria.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos para crear o modificar una categoria")
public record CreateCategoriaRequestDto(
        @Schema(description = "Nombre único de la categoría", example = "Cases")
        @NotBlank String nombre
) {
}
