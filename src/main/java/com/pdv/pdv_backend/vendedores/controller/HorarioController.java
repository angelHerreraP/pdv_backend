package com.pdv.pdv_backend.vendedores.controller;

import com.pdv.pdv_backend.vendedores.dto.request.CrearHorarioRequestDto;
import com.pdv.pdv_backend.vendedores.dto.request.UpdateHorarioRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.HorarioResponseDto;
import com.pdv.pdv_backend.vendedores.service.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horario")
@RequiredArgsConstructor
public class HorarioController {
    private final HorarioService horarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HorarioResponseDto> crearHorario(@RequestBody CrearHorarioRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioService.crearHorario(dto));
    }

    @PatchMapping("/vendedor/{vendedorId}")
    public ResponseEntity<HorarioResponseDto> modificarHorario(@PathVariable Long vendedorId, @RequestBody UpdateHorarioRequestDto dto){
        return ResponseEntity.ok(horarioService.editarHorario(vendedorId, dto));
    }
}
