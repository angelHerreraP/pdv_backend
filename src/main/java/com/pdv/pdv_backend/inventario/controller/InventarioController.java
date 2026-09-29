package com.pdv.pdv_backend.inventario.controller;

import com.pdv.pdv_backend.inventario.dto.request.AjustarInventarioDto;
import com.pdv.pdv_backend.inventario.dto.response.InventarioResponseDto;
import com.pdv.pdv_backend.inventario.service.InventarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("sucursal/{sucursalId}")
    public List<InventarioResponseDto> listarPorSucursal(@PathVariable Long sucursalId){
        return inventarioService.inventarioDeSucursal(sucursalId);
    }

    @PatchMapping("/añadir")
    public InventarioResponseDto aumentar(@RequestBody AjustarInventarioDto dto){
        return inventarioService.aumentarInventario(dto);
    }

    @PatchMapping("/reducir")
    public InventarioResponseDto reducir(@RequestBody AjustarInventarioDto dto){
        return inventarioService.reducirInventario(dto);
    }

}
