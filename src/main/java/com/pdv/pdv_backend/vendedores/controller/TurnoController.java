package com.pdv.pdv_backend.vendedores.controller;

import com.pdv.pdv_backend.vendedores.dto.response.TurnoResponseDto;
import com.pdv.pdv_backend.vendedores.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
@RequiredArgsConstructor
public class TurnoController {
    private final TurnoService turnoService;



    @GetMapping("/all")
    public ResponseEntity<List<TurnoResponseDto>> listarVendedoresActivosHoy(){
        return ResponseEntity.ok(turnoService.listarTodosLosVendedoresActivos());
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<TurnoResponseDto>> listarVendedoresActivosEnSucursal(@PathVariable Long sucursalId){
        return ResponseEntity.ok(turnoService.listarVendedoresActivosEnSucursal(sucursalId));
    }


}
