package com.pdv.pdv_backend.vendedores.dto.response;

import java.time.LocalTime;

public record HorarioResponseDto(
        Long id,
        Long vendedorId,
        LocalTime horaEntradaEsperada,
        LocalTime horaSalidaEsperada
) {
}
