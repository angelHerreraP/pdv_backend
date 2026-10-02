package com.pdv.pdv_backend.inventario.controller;

import com.pdv.pdv_backend.auth.service.JwtService;
import com.pdv.pdv_backend.inventario.dto.request.AjustarInventarioDto;
import com.pdv.pdv_backend.inventario.dto.response.InventarioResponseDto;
import com.pdv.pdv_backend.inventario.service.InventarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    private final InventarioService inventarioService;
    private final JwtService jwtService;

    public InventarioController(InventarioService inventarioService, JwtService jwtService) {
        this.inventarioService = inventarioService;
        this.jwtService = jwtService;
    }

    // Buscar inventario por sucursal (Sucursales su inventario, admin
    @GetMapping("/sucursal/{sucursalId}")
    public List<InventarioResponseDto> listarPorSucursal(@PathVariable Long sucursalId,
                                                         HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String rol = jwtService.extraerRol(token);
        Long sucursalIdDelToken = jwtService.extraeSucursalId(token);

        return inventarioService.inventarioDeSucursal(sucursalId, rol, sucursalIdDelToken);
    }


    @PatchMapping("/añadir")
    public InventarioResponseDto aumentar(@RequestBody AjustarInventarioDto dto){
        return inventarioService.aumentarInventario(dto);
    }

    @PatchMapping("/reducir")
    public InventarioResponseDto reducir(@RequestBody AjustarInventarioDto dto){
        return inventarioService.reducirInventario(dto);
    }

    @GetMapping("/all")
    public  List<InventarioResponseDto> listarTodo(){
        return inventarioService.listarTodo();
    }

}
