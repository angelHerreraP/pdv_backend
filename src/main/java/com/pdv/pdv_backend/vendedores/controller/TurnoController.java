package com.pdv.pdv_backend.vendedores.controller;

import com.pdv.pdv_backend.vendedores.dto.request.CrearTurnoRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.TurnoResponseDto;
import com.pdv.pdv_backend.vendedores.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TurnoResponseDto abrirTurno(@RequestBody CrearTurnoRequestDto dto){
        return turnoService.marcarEntrada(dto);
    }

    @PatchMapping("/activo/{vendedorId}")
    public TurnoResponseDto cerrarTurno(@PathVariable Long vendedorId){
        return turnoService.marcarSalida(vendedorId);
    }

    @GetMapping("/all")
    public List<TurnoResponseDto> listarVendedoresActivosHoy(){
        return turnoService.listarTodosLosVendedoresActivos();
    }

    @GetMapping("/sucursal/{sucursalId}")
    public List<TurnoResponseDto> listarVendedoresActivosEnSucursal(@PathVariable Long sucursalId){
        return turnoService.listarVendedoresActivosEnSucursal(sucursalId);
    }


}
