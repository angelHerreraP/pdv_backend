package com.pdv.pdv_backend.vendedores.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AsistenciaRequestDto(
        @NotBlank String codigoBarras,
        @NotNull Long sucursalId
) {
}
