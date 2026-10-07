package com.pdv.pdv_backend.autorizations.dto.request;

import com.pdv.pdv_backend.autorizations.constats.AccionAutorizada;

import java.util.Map;

public record CreateCodigoRequestDto(
        AccionAutorizada accion,
        Map<String, Object> parametros
) {
}
