package com.pdv.pdv_backend.auth.dto.request;

public record CreateUsuarioRequestDto(
        String nombre,
        String usuario,
        String password,
        Long rolId,
        Long sucursalId
) {
}
