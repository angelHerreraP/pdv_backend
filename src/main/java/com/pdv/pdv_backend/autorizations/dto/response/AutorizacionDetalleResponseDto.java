package com.pdv.pdv_backend.autorizations.dto.response;

import com.pdv.pdv_backend.autorizations.constats.AccionAutorizada;

import java.time.LocalDateTime;
import java.util.Map;

public record AutorizacionDetalleResponseDto(
        Long id,
        String codigo,
        AccionAutorizada accion,
        Map<String, Object> parametros,
        String creadoPorNombre,
        LocalDateTime creadoEn,
        LocalDateTime expiraEn,
        Boolean usado,
        LocalDateTime usadoEn
) {
}
