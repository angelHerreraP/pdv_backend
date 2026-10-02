package com.pdv.pdv_backend.vendedores.dto.request;

import java.time.LocalDateTime;

public record CrearTurnoRequestDto(
        Long vendedorId,
        Long sucursalId
) {
}
