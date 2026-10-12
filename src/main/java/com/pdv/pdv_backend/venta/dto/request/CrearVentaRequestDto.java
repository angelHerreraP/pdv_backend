package com.pdv.pdv_backend.venta.dto.request;

import com.pdv.pdv_backend.venta.constants.MetodoPago;
import com.pdv.pdv_backend.venta.entity.PagoVenta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CrearVentaRequestDto(
        @NotNull Long vendedorId,
        @NotNull Long sucursalId,
        @NotEmpty @Valid List<DetalleVentaRequestDto> productos,
        @NotEmpty List<PagoVentaRequestDto> pagos,
        @Valid CreditoVentaRequestDto credito, // si es null, no fue a credito
        @Size(max = 20) String codigoAutorizacion // si es null, no hay actualizacion

) {
}
