package com.pdv.pdv_backend.compra.dto.request;

public record DetallecompraRequestDto(
        Long productoId,
        Double costoUnitario,
        Integer cantidad
) {
}
