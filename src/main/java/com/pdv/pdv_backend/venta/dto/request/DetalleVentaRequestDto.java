package com.pdv.pdv_backend.venta.dto.request;

public record DetalleVentaRequestDto(
        Long productoId,
        Integer cantidad
) {
}
