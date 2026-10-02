package com.pdv.pdv_backend.compra.dto.request;

import java.util.List;

public record CreateCompraRequestDto(
        Long proveedorId,
        Long sucursalId,
        List<DetallecompraRequestDto> productos
) {
}
