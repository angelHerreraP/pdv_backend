package com.pdv.pdv_backend.auth.dto.response;

public record LoginResponseDto(
        String token,
        String usuario,
        String rol
) {
}
