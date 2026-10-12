package com.pdv.pdv_backend.vendedores.config;

import com.pdv.pdv_backend.vendedores.repository.TurnoVendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class CierreAutomaticoTurnos{
    private final TurnoVendedorRepository turnoVendedorRepository;

    @Scheduled(cron = "0 0 * * * *", zone = "America/Mexico_City")
    @Transactional
    public void cerrarTurnoVencido(){
        LocalDateTime ahora = LocalDateTime.now(ZoneId.of("America/Mexico_City"));
        LocalDateTime inicioHoy = ahora.toLocalDate().atStartOfDay();
        LocalDateTime corte22 = ahora.toLocalDate().atTime(22, 0);

        turnoVendedorRepository.findByHoraSalidaIsNullAndHoraEntradaBefore(inicioHoy)
                .forEach(t -> t.setHoraSalida(t.getHoraEntrada().toLocalDate().atTime(22, 0)));

        // 2. turnos de hoy, solo si ya pasaron las 22:00
        if (!ahora.isBefore(corte22)) {
            turnoVendedorRepository.findByHoraSalidaIsNull()
                    .forEach(t -> t.setHoraSalida(corte22));
        }
    }

}