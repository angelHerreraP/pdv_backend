package com.pdv.pdv_backend.sucursal.dto.request;

import java.time.LocalDateTime;

public record CreateSucursalRequestDto(
        String nombre,
        String plaza,
        String direccion
) {
}
