package com.pdv.pdv_backend.autorizations.dto.request;

import jakarta.validation.constraints.NotNull;

public record CrearCodigoEliminarVentaRequestDto(
        @NotNull Long ventaId
) {
}
