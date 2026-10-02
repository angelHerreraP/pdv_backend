package com.pdv.pdv_backend.vendedores.controller;

import com.pdv.pdv_backend.vendedores.dto.request.CrearVendedorRequestDto;
import com.pdv.pdv_backend.vendedores.dto.request.UpdateVendedorRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.VendedorResponseDto;
import com.pdv.pdv_backend.vendedores.service.VendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vendedor")
@RestController
public class VendedorController {
    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendedorResponseDto crear(@RequestBody CrearVendedorRequestDto dto){
        return vendedorService.crearVendedor(dto);
    }

    @PatchMapping("/{vendedorId}")
    public VendedorResponseDto modificar(@RequestBody UpdateVendedorRequestDto dto, @PathVariable Long vendedorId){
        return vendedorService.modificarVendedor(vendedorId, dto);
    }

    @GetMapping("/all")
    public List<VendedorResponseDto> listarTodos(){
        return vendedorService.vendedoresActivos();
    }

    @GetMapping("/codigo/{codigoBarras}")
    public VendedorResponseDto buscarVendedor(@PathVariable String codigoBarras){
        return vendedorService.buscarPorCodigoBarras(codigoBarras);
    }
}
