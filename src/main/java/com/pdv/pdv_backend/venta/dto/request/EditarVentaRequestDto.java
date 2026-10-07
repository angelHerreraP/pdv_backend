package com.pdv.pdv_backend.venta.dto.request;

import com.pdv.pdv_backend.venta.dto.response.DetalleVentaResponseDto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record EditarVentaRequestDto(
        @NotBlank String codigoAutorizacion,
        Long vendedorId,
        List<DetalleVentaRequestDto> productos
) {
}
