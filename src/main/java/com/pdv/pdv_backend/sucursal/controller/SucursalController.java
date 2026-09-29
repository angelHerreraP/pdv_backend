package com.pdv.pdv_backend.sucursal.controller;

import com.pdv.pdv_backend.sucursal.dto.request.CreateSucursalRequestDto;
import com.pdv.pdv_backend.sucursal.dto.response.SucursalResponseDto;
import com.pdv.pdv_backend.sucursal.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService){
        this.sucursalService = sucursalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SucursalResponseDto crearSucursal(@RequestBody CreateSucursalRequestDto dto){
        return sucursalService.createSucursal(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSucursal(@PathVariable Long id){
        sucursalService.eliminarSucural(id);
    }

    @GetMapping
    public List<SucursalResponseDto> sucursales(){
        return sucursalService.sucursales();
    }

    @PatchMapping("/{id}")
    public SucursalResponseDto editarSucursal(@PathVariable Long id, @RequestBody CreateSucursalRequestDto dto){
        return sucursalService.actualizarSucursal(id, dto);
    }
}
