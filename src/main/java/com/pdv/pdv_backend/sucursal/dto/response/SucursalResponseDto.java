package com.pdv.pdv_backend.sucursal.dto.response;

import java.time.LocalDateTime;

public record SucursalResponseDto(
        Long id,
        String nombre,
        String plaza,
        String direccion,
        LocalDateTime creadoEn
) {
}
