package com.pdv.pdv_backend.proveedor.controller;

import com.pdv.pdv_backend.proveedor.dto.request.CreateProveedorRequestDto;
import com.pdv.pdv_backend.proveedor.dto.response.ProveedorResponseDto;
import com.pdv.pdv_backend.proveedor.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProveedorResponseDto addProvider(@RequestBody CreateProveedorRequestDto dto){
        return proveedorService.agregarProveedor(dto);
    }


    @PatchMapping("/{proveedorId}")
    public ProveedorResponseDto updateProvider(@PathVariable Long proveedorId, @RequestBody CreateProveedorRequestDto dto){
        return proveedorService.updateProveedor(proveedorId, dto);
    }

    @DeleteMapping("/{proveedorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProvider(@PathVariable Long proveedorId){
        proveedorService.deleteProveedor(proveedorId);
    }

    @GetMapping("/all")
    public List<ProveedorResponseDto> listProviders(){
        return proveedorService.listarProveedores();
    }
}
