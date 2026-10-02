package com.pdv.pdv_backend.proveedor.dto.request;

public record CreateProveedorRequestDto(
        String nombre,
        String nombreContacto,
        String telefonoContacto
) {
}
