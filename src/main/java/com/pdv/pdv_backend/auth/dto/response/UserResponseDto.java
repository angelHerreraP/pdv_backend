package com.pdv.pdv_backend.auth.dto.response;

public record UserResponseDto(
        Long id,
        String nombre,
        String usuario,
        String rolName,
        Long sucursalId,
        String sucursalName
) {
}
