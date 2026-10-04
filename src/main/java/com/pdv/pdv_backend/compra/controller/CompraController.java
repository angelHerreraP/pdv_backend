package com.pdv.pdv_backend.compra.controller;

import com.pdv.pdv_backend.compra.dto.request.CreateCompraRequestDto;
import com.pdv.pdv_backend.compra.dto.response.CompraResponseDto;
import com.pdv.pdv_backend.compra.service.CompraService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {
    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompraResponseDto compra(@RequestBody CreateCompraRequestDto dto){
        return compraService.crearCompra(dto);
    }
    @GetMapping("/all")
    public List<CompraResponseDto> listaDeCompras(){
        return compraService.listarCompras();
    }

}
