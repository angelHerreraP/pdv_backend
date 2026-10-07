package com.pdv.pdv_backend.venta.dto.request;

import java.util.List;

public record CrearVentaRequestDto(
        Long vendedorId,
        Long sucursalId,
        String metodoPago,
        String folioCredito,
        String codigoDescuento, //es nullable
        List<DetalleVentaRequestDto> productos
) {
}
