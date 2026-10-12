package com.pdv.pdv_backend.venta.dto.request;

import com.pdv.pdv_backend.financiera.entity.Financiera;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreditoVentaRequestDto(
        @NotNull Long financieraId,
        @NotNull @DecimalMin("0.00") BigDecimal enganche,
        @NotNull @DecimalMin("0.01") BigDecimal pagoSemanal,
        @NotBlank @Size(max = 100) String codigoPago
) {
}
