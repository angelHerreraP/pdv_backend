package com.pdv.pdv_backend.venta.dto.request;

import com.pdv.pdv_backend.venta.constants.MetodoPago;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PagoVentaRequestDto(
        @NotBlank MetodoPago metodoPago,
        @NotNull @DecimalMin("0.01") BigDecimal monto,
        @Size(max = 50) String referencia
) {
}
