package com.pdv.pdv_backend.vendedores.dto.response;

import java.math.BigDecimal;

public record VendedorResponseDto(
        Long id,
        String nombre,
        String codigoBarras,
        BigDecimal salarioSemanal,
        String diaDescanso,
        Boolean activo
) {
}
