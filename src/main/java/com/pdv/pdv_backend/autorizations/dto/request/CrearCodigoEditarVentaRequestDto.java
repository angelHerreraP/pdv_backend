package com.pdv.pdv_backend.autorizations.dto.request;

import com.pdv.pdv_backend.producto.entity.Producto;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.OnClose;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public record CrearCodigoEditarVentaRequestDto(
        @NotNull Long ventaId
) {
}
