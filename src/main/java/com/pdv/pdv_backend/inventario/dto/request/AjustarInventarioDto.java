package com.pdv.pdv_backend.inventario.dto.request;

public record AjustarInventarioDto(
        Long productoId,
        Long sucursalId,
        Integer cantidad
) {
}
