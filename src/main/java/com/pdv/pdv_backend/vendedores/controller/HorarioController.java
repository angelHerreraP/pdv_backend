package com.pdv.pdv_backend.vendedores.controller;

import com.pdv.pdv_backend.vendedores.dto.request.CrearHorarioRequestDto;
import com.pdv.pdv_backend.vendedores.dto.request.UpdateHorarioRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.HorarioResponseDto;
import com.pdv.pdv_backend.vendedores.service.HorarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horario")
public class HorarioController {
    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioResponseDto crearHorario(@RequestBody CrearHorarioRequestDto dto){
        return horarioService.crearHorario(dto);
    }

    @PatchMapping("/vendedor/{vendedorId}")
    public HorarioResponseDto modificarHorario(@PathVariable Long vendedorId, @RequestBody UpdateHorarioRequestDto dto){
        return horarioService.editarHorario(vendedorId, dto);
    }
}
