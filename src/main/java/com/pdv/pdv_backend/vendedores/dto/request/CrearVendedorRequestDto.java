package com.pdv.pdv_backend.vendedores.dto.request;


public record CrearVendedorRequestDto(
        String nombre,
        Double salarioSemanal,
        String diaDescanso,
        Boolean activo
) {
}
