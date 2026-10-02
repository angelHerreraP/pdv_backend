package com.pdv.pdv_backend.proveedor.dto.response;

import java.time.LocalDateTime;

public record ProveedorResponseDto(
        Long id,
        String nombre,
        String nombreContacto,
        String telefonoContacto,
        LocalDateTime creadoEn
) {
}
