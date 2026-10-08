package com.pdv.pdv_backend.vendedores.dto.request;

import jakarta.validation.constraints.Positive;

public record UpdateVendedorRequestDto(
        String nombre,
        @Positive Double salarioSemanal,
        String diaDescanso,
        Boolean activo
) {}