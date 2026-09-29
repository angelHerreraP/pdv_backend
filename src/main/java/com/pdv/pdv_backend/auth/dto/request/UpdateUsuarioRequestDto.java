package com.pdv.pdv_backend.auth.dto.request;

public record UpdateUsuarioRequestDto(
        String nombre,
        String password,
        Long rolId,
        Long sucursalId
) {
}
