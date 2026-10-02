package com.pdv.pdv_backend.vendedores.dto.response;
import java.time.LocalDateTime;

public record TurnoResponseDto(
        Long id,
        Long vendedorId,
        Long sucursalId,
        LocalDateTime horaEntrada,
        LocalDateTime horaSalida
) {
}
