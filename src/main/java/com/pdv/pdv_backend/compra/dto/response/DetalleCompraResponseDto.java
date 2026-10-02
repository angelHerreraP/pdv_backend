package com.pdv.pdv_backend.compra.dto.response;

public record DetalleCompraResponseDto(
        Long productoId,
        String productoName,
        Double costoUnitario,
        Integer cantidad
) {
}
