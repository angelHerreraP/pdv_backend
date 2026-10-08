package com.pdv.pdv_backend.vendedores.controller;

import com.pdv.pdv_backend.vendedores.dto.request.CrearVendedorRequestDto;
import com.pdv.pdv_backend.vendedores.dto.request.UpdateVendedorRequestDto;
import com.pdv.pdv_backend.vendedores.dto.response.VendedorResponseDto;
import com.pdv.pdv_backend.vendedores.service.VendedorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vendedor")
@RestController
@RequiredArgsConstructor
@Tag(name = "Vendedor", description = "Gestion de vendedores en el Punto de venta.")
public class VendedorController {
    private final VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<VendedorResponseDto> crear(@RequestBody CrearVendedorRequestDto dto){
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
