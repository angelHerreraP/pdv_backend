package com.pdv.pdv_backend.producto.dto.response;

import java.math.BigDecimal;

public record ProductoResponse(
        Long id,
        String nombre,
        String marcaNombe,
        String categoriaNombre,
        BigDecimal precioPublico,
        Boolean requiereSerie,
        String codigoBarras
) {
}
