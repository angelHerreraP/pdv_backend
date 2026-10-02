package com.pdv.pdv_backend.vendedores.dto.request;

import java.time.LocalTime;

public record CrearHorarioRequestDto(
        Long vendedorId,
        LocalTime horaEntradaEsperada,
        LocalTime horaSalidaEsperada
) {
}
