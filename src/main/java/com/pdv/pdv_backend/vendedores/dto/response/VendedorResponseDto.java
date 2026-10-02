package com.pdv.pdv_backend.vendedores.dto.response;

public record VendedorResponseDto(
        Long id,
        String nombre,
        String codigoBarras,
        Double salarioSemanal,
        String diaDescanso,
        Boolean activo
) {
}
