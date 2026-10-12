package com.pdv.pdv_backend.venta.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleVentaRequestDto(
        @NotNull Long productoId,
        @NotNull @Min(1) Integer cantidad,
        Boolean regalo
) {
}
