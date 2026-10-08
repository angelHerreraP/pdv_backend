package com.pdv.pdv_backend.vendedores.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CrearVendedorRequestDto(
        @NotBlank String nombre,
        @Positive @NotNull BigDecimal salarioSemanal,
        @NotBlank String diaDescanso
) {
}
