package com.pdv.pdv_backend.autorizations.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CrearCodigoDescuentoRequestDto(
        @NotNull Boolean esPorcentaje,
        @NotNull @Positive BigDecimal valor
) {
}
