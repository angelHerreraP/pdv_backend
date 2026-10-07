package com.pdv.pdv_backend.autorizations.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearCodigoTraspasoRequestDto(
        @NotNull Long origenId,
        @NotNull Long destinoId,
        @NotNull Long productoId,
        @NotNull @Positive Integer cantidad
) {
}
