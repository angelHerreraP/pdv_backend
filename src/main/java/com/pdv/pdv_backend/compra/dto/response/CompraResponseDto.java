package com.pdv.pdv_backend.compra.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record CompraResponseDto(
        Long id,
        String proveedorName,
        String sucursalName,
        LocalDateTime fecha,
        List<DetalleCompraResponseDto> productos
) {
}
