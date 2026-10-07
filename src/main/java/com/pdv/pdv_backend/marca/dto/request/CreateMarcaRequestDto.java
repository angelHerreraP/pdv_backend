package com.pdv.pdv_backend.marca.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Schema para editar o crear una marca")
public record CreateMarcaRequestDto(
        @Schema(description = "Nombre único de la marca", example = "Samsung")
        @NotBlank String nombre
) {
}
