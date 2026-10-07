package com.pdv.pdv_backend.venta.dto.response;

import java.math.BigDecimal;

public record DetalleVentaResponseDto(
        Long productoId,
        String productoName,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal
) {
}
