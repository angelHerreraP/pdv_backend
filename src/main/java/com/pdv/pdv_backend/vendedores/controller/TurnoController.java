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


    @GetMapping("/all")
    public List<TurnoResponseDto> listarVendedoresActivosHoy(){
        return turnoService.listarTodosLosVendedoresActivos();
    }

    @GetMapping("/sucursal/{sucursalId}")
    public List<TurnoResponseDto> listarVendedoresActivosEnSucursal(@PathVariable Long sucursalId){
        return turnoService.listarVendedoresActivosEnSucursal(sucursalId);
    }


}
