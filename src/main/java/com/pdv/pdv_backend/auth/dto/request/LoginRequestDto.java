package com.pdv.pdv_backend.auth.dto.request;

public record LoginRequestDto(
        String usuario,
        String password
) {
}
