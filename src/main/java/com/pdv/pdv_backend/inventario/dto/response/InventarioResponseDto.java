package com.pdv.pdv_backend.inventario.dto.response;

public record InventarioResponseDto(
        Long id,
        String productoName,
        String sucursalName,
        Integer cantidad
) {
}
