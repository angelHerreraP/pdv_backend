package com.pdv.pdv_backend.vendedores.dto.request;


import java.time.LocalTime;

public record UpdateHorarioRequestDto(
        LocalTime horaEntradaEsperada,
        LocalTime horaSalidaEsperada
) {}