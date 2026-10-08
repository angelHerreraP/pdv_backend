
@Component
@RequiredArgsConstructor
public class CierreAutomaticoTurno{
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

}