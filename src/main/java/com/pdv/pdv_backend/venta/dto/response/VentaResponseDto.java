package com.pdv.pdv_backend.venta.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDto(
        Long id,
        String vendedorNombre,
        String sucursalNombre,
        String metodoPago,
        String folioCredito,
        BigDecimal subtotal,
        BigDecimal descuento,
        BigDecimal total,
        LocalDateTime fecha,
        List<DetalleVentaResponseDto> productos

) {
}
