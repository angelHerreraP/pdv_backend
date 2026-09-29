package com.pdv.pdv_backend.producto.dto.request;

import java.math.BigDecimal;

public record CreateProductRequestDto(
        String nombre,
        Long marcaId,
        Long categoriaId,
        BigDecimal precioPublico,
        Boolean requiereSerie,
        String codigoBarras
) {
}
