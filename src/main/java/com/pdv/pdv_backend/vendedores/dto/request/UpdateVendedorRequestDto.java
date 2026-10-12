package com.pdv.pdv_backend.vendedores.dto.request;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateVendedorRequestDto(
        String nombre,
        @Positive BigDecimal salarioSemanal,
        String diaDescanso,
        Boolean activo
) {}